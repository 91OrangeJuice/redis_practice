package com.tkp.learn.web.model.enums;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/3
 * @version: 1.0
 */
public enum  PraisesStatusEnum {
    DELETE("99","删除"),
    UNREAD("01","未读"),
    READED("02","已读");
    private String code;
    private String name;

    PraisesStatusEnum(final String code,final String name) {
        this.code=code;
        this.name=name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
