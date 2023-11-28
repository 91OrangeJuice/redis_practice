package com.tkp.learn.admin.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tkp.learn.admin.actuator.exception.SystemException;
import com.tkp.learn.admin.actuator.model.ErrorCode;
import com.tkp.learn.admin.actuator.model.ViewBean;
import com.tkp.learn.admin.model.common.JwtResultBo;
import com.tkp.learn.admin.model.enums.IdentityEnum;
import com.tkp.learn.admin.model.enums.TkopUserTypeEnum;
import com.tkp.learn.admin.remote.dto.tkpop.TkopUserDto;
import com.tkp.learn.admin.security.JwtTokenProvider;
import com.tkp.learn.admin.service.tkpes.TkpopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private JwtTokenProvider jwtTokenProvider;

    private TkpopService tkpopService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,TkpopService tkpopService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.tkpopService = tkpopService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        if (StringUtils.isEmpty(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }
        JwtResultBo result = jwtTokenProvider.parseJWT(jwt);
        LOGGER.info("jwt解析结果[{}]", result);
        if (!StringUtils.isEmpty(result)) {
            LOGGER.info("jwtToken [{}] 有效", jwt);
            TkopUserDto customer = tkpopService.getUser(result.getData());
            buildTkopUserBo(customer);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());//用户名 密码 权限列表
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            LOGGER.info("jwtToken [{}] 已过期", jwt);
            filterChain.doFilter(request, response);
        }
    }


    private void buildTkopUserBo(TkopUserDto customer){
        if(TkopUserTypeEnum.EMPLOYEE.getCode().equals(customer.getUserType())){
            customer.setIdentityEnum(IdentityEnum.EMPLOYEE);
        }
        if(TkopUserTypeEnum.SALESMAN.getCode().equals(customer.getUserType())){
            customer.setIdentityEnum(IdentityEnum.SALESMAN);
        }
        if(!ObjectUtils.isEmpty(customer.getOrg())&&customer.getOrg().length()==4){
            customer.setBranchCode(customer.getOrg().substring(0,2));
        }else{
            customer.setBranchCode(customer.getOrg());
        }
    }


    /**
     * 获取JWT
     *
     * @param request
     * @return
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(jwt)) {
            return jwt;
        }
        return null;
    }

}
