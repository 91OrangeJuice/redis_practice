package com.tkp.learn.web.model.enums;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/3
 * @version: 1.0
 */
public enum OrderConditionEnum {
    newly("new","最新"),
    hot("hot","热门");

    private String code;
    private String name;

    OrderConditionEnum(final String code, final String name) {
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
