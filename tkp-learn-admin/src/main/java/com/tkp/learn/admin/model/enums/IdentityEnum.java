package com.tkp.learn.admin.model.enums;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */
public enum IdentityEnum {

    EMPLOYEE("EMPLOYEE", "内勤"),
    SALESMAN("SALESMAN","外勤");

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
