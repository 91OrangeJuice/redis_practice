package com.tkp.learn.pushcdn.task.remote.dto;

/**
 * Created by itw_wangshuai01 on 2020/6/9.
 */

public class UploadFileRequest {

    //文件字节数组
    private byte[] bytes;
    //文件名称
    private String filename;
    //文件类型
    private String fileproperty;
    //文件来源
    private String source;
    //文件相对路径
    private String filepath;
    //文件相对路径
    private boolean iscover;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileproperty() {
        return fileproperty;
    }

    public void setFileproperty(String fileproperty) {
        this.fileproperty = fileproperty;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public boolean iscover() {
        return iscover;
    }

    public void setIscover(boolean iscover) {
        this.iscover = iscover;
    }

    @Override
    public String toString() {
        return "UploadFileRequest{" +
                ", filename='" + filename + '\'' +
                ", fileproperty='" + fileproperty + '\'' +
                ", source='" + source + '\'' +
                ", filepath='" + filepath + '\'' +
                ", iscover=" + iscover +
                '}';
    }
}
