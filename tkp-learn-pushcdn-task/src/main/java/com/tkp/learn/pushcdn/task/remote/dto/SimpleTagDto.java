package com.tkp.learn.pushcdn.task.remote.dto;

/**
 * Created by itw_wangshuai01 on 2020/6/15.
 */
public class SimpleTagDto {

    private String tagCode;
    private String tagName;

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "SimpleTagDto{" +
                "tagCode='" + tagCode + '\'' +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
