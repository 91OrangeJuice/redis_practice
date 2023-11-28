package com.tkp.learn.admin.actuator.model;

import lombok.ToString;

@ToString
public class ViewBean<T> {

    private boolean success = true;

    private int code;

    private String errorMessage;

    private T data;

    public ViewBean() {

    }

    public static <T> ViewBean<T> createSuccess(final T data) {
        return new ViewBean<>(data);
    }

    public static <T> ViewBean<T> createFail(final String errorMessage,final ErrorCode errorCode) {
        final ViewBean<T> viewBean = new ViewBean<>();
        viewBean.setSuccess(false);
        viewBean.setCode(errorCode.getCode());
        viewBean.setErrorMessage(errorMessage);
        return viewBean;
    }

    public static <T> ViewBean<T> createFail(final ErrorCode errorCode) {
        final ViewBean<T> viewBean = new ViewBean<>();
        viewBean.setSuccess(false);
        viewBean.setCode(errorCode.getCode());
        viewBean.setErrorMessage(errorCode.getName());
        return viewBean;
    }

    public ViewBean(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
