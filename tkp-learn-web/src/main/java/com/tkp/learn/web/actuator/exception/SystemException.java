package com.tkp.learn.web.actuator.exception;

import com.tkp.learn.web.actuator.model.ErrorCode;

import java.text.MessageFormat;


/**
 * 自定义系统异常类
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 8355996407784191778L;

    private ErrorCode errorCode = null;

    private static final String DEFAULT_EXCEPTION_HANDLER = "default_handler";

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getHandlerName() {
        return DEFAULT_EXCEPTION_HANDLER;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        if (this.errorCode == null) {
            return ErrorCode.SERVICE_ERROR;
        }
        return errorCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]", this.errorCode.getName(), this.errorCode.getCode());
    }

}
