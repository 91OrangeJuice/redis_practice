package com.tkp.learn.web.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义用户认证过期处理组件
 * @author itw_mapp
 *
 */
@Component
public class AuthenticationEntryPointFilter implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationEntryPointFilter.class);


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        LOGGER.info("未登录或登录已超时 Message-{},",e.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"对不起您还没有权限访问！");
    }

}
