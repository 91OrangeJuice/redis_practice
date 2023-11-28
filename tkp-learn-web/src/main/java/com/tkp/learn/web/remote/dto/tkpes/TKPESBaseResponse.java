package com.tkp.learn.web.remote.dto.tkpes;

/**
 * @Description:
 * @ClassName: TKPESBaseResponse
 * @author: itw_wangshuai01
 * @date: 2019/7/17
 */
public class TKPESBaseResponse<T> {

    private boolean success;
    private int errorCode;
    private String errorMessage;

    private T content;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
