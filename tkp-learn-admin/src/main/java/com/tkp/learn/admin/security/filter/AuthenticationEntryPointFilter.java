package com.tkp.learn.admin.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tkp.learn.admin.actuator.exception.SystemException;
import com.tkp.learn.admin.actuator.model.ErrorCode;
import com.tkp.learn.admin.actuator.model.ViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        response.setStatus(200);
        try {
            responseJson(response, ViewBean.createFail("登录超时或未登入", ErrorCode.NO_LOGIN));
        } catch (Exception ee) {
            LOGGER.error("拦截权限返回结果异常", ee);
            throw new SystemException("拦截权限返回结果异常",ee);
        }
    }

    /**
     * 返回JSON数据
     * @param response
     * @param obj
     * @throws Exception
     */
    private void responseJson(HttpServletResponse response, Object obj) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat));
        writer.close();
        response.flushBuffer();
    }

}
