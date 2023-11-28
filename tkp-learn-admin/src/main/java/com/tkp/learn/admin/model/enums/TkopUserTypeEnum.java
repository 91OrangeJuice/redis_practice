package com.tkp.learn.admin.model.enums;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/28
 * @version: 1.0
 */
public enum TkopUserTypeEnum {

    EMPLOYEE("10", "内勤"),
    SALESMAN("11","外勤");

    private String code;

    private String name;

    TkopUserTypeEnum(final String code, final String name) {
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
