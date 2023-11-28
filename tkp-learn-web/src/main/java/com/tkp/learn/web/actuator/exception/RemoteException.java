package com.tkp.learn.web.actuator.exception;

import com.tkp.learn.web.actuator.model.ErrorCode;

/**
 * 自定义远程接口异常类
 * 封装远程接口异常信息
 */
public class RemoteException extends SystemException {

    private static final long serialVersionUID = -2003055708842127547L;

    public RemoteException(String message) {
        super(message);
    }

    public RemoteException(String message, ErrorCode errorCode) {
        super(message);
        super.setErrorCode(errorCode);

    }

    public RemoteException(Throwable cause) {
        super(cause);
    }

    public RemoteException(String message, Throwable cause) {
        super(message, cause);
    }
}