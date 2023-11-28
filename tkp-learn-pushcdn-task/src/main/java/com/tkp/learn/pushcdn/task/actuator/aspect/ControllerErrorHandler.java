package com.tkp.learn.pushcdn.task.actuator.aspect;


import com.tkp.learn.pushcdn.task.actuator.exception.SystemException;
import com.tkp.learn.pushcdn.task.actuator.handler.ExceptionHandlerFactory;
import com.tkp.learn.pushcdn.task.actuator.model.ErrorCode;
import com.tkp.learn.pushcdn.task.actuator.model.ViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

/**
 * Created by itw_wangshuai01 on 2020/4/8.
 */

@ControllerAdvice
public class ControllerErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerErrorHandler.class);

    @Autowired
    private ExceptionHandlerFactory exceptionHandlerFactory;

    @ResponseBody
    @ExceptionHandler(SystemException.class)
    public Object couponExceptionHandler(SystemException exception) {
        final String handlerName = exception.getHandlerName();
        com.tkp.learn.pushcdn.task.actuator.handler.ExceptionHandler exceptionHandler = exceptionHandlerFactory.getHandler(handlerName);
        LOGGER.error("接口请求出现异常,异常原因：{}", exception.getMessage());
        return exceptionHandler.dutyHandler(exception);
    }

    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public Object missingHandler(AccessDeniedException e) {
        final String errorMsg = e.getMessage();
        LOGGER.warn("ACCESS＿DENIED，[{}]", errorMsg);
        return ViewBean.createFail(ErrorCode.NO_LOGIN.getName(), ErrorCode.NO_LOGIN);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object missingHandler(MissingServletRequestParameterException e) {
        final String errorMsg = e.getMessage();
        LOGGER.warn("BAD_REQUEST[{}]", errorMsg);
        return ViewBean.createFail(errorMsg, ErrorCode.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object missingHandler(HttpMessageNotReadableException e) {
        final String errorMsg = e.getMessage();
        LOGGER.warn("BAD_REQUEST[{}]", errorMsg);
        return ViewBean.createFail(errorMsg, ErrorCode.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object methodNotSupportedHandler(HttpRequestMethodNotSupportedException e) {
        final String errorMsg = e.getMessage();
        LOGGER.warn("Method Not Allowed[{}]", errorMsg);
        return ViewBean.createFail(errorMsg, ErrorCode.METHOD_NOT_ALLOWED);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(Exception e) {
        LOGGER.error("出现系统无法处理的异常！请联系服务人员及时处理!", e.fillInStackTrace());
        return ViewBean.createFail(ErrorCode.FAIL);
    }
}
