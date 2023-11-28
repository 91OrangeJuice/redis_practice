package com.tkp.learn.pushcdn.task.remote.dto;


import java.util.List;

/**
 * Created by itw_wangshuai01 on 2020/6/9.
 */

public class UploadFileResponse {

    private Boolean success;
    private String errorCode;
    private String errorMsg;
    private List<String> errorMsgList;
    private Boolean data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<String> getErrorMsgList() {
        return errorMsgList;
    }

    public void setErrorMsgList(List<String> errorMsgList) {
        this.errorMsgList = errorMsgList;
    }

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UploadFileResponse{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", errorMsgList=" + errorMsgList +
                ", data=" + data +
                '}';
    }
}
