package com.tkp.learn.web.model.enums;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */
public enum IdentityEnum {

    EMPLOYEE("employee", "内勤"),
    SALESMAN("salesman","外勤");

    private String code;

    private String name;

    IdentityEnum(final String code, final String name) {
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
