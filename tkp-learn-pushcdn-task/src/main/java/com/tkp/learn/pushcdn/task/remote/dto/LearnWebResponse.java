package com.tkp.learn.pushcdn.task.remote.dto;

/**
 * Created by itw_wangshuai01 on 2020/6/15.
 */

public class LearnWebResponse<T> {

    private Boolean success;

    private int code;

    private String errorMessage;

    private T data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
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
