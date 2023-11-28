package com.tkp.learn.web.model.enums;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/3
 * @version: 1.0
 */
public enum SerchConditionEnum {
    myOrg("myOrg","本机构");
    private String code;
    private String name;

    SerchConditionEnum(final String code, final String name) {
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
