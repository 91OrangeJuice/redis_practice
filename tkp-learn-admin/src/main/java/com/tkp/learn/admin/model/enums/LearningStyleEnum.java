package com.tkp.learn.admin.model.enums;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/29
 * @version: 1.0
 */
public enum LearningStyleEnum {
    COMPUTER("COMPUTER", "电脑"),
    WECHAT("WECHAT","微信"),
    CLIENT("CLIENT","客户端"),
    OTHERS("OTHERS","其他");

    private String code;

    private String name;

    LearningStyleEnum(final String code, final String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
