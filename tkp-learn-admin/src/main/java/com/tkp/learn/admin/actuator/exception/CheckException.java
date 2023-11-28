package com.tkp.learn.admin.actuator.exception;


import com.tkp.learn.admin.actuator.model.ErrorCode;

/**
 * 自定义参数校验异常类
 * 校验数据的合法性
 */
public class CheckException extends SystemException {

    private static final long serialVersionUID = 8355996407784190778L;

    public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, ErrorCode errorCode) {
        super(message);
        super.setErrorCode(errorCode);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

}
