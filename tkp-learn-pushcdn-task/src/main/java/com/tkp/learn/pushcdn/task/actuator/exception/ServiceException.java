package com.tkp.learn.pushcdn.task.actuator.exception;


import com.tkp.learn.pushcdn.task.actuator.model.ErrorCode;

/**
 * 自定义业务异常类
 * 校验业务逻辑的合法性
 */
public class ServiceException extends SystemException {

    private static final long serialVersionUID = 7816285647719283421L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, ErrorCode errorCode) {
        super(message);
        super.setErrorCode(errorCode);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
