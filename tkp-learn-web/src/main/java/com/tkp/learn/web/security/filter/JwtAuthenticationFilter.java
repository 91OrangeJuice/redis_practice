package com.tkp.learn.web.security.filter;

import com.tkp.learn.web.model.bo.JwtResultBo;
import com.tkp.learn.web.security.JwtTokenProvider;
import com.tkp.learn.web.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
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
            SecurityUser customer = jwtTokenProvider.loadUserById(result);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());//用户名 密码 权限列表
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            LOGGER.info("jwtToken [{}] 已过期", jwt);
            filterChain.doFilter(request, response);
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
