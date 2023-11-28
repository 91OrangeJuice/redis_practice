package com.tkp.learn.web.model.enums;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/3
 * @version: 1.0
 */
public enum DurationUintEnum {

    DAYS("DAYS","天"),
    HOURS("HOURS","小时"),
    MINUTES("MINUTES","分钟"),
    SECONDS("SECONDS","秒"),
    WEEKS("WEEKS","周"),
    YEARS("YEARS","年");

    private String code;
    private String name;

    DurationUintEnum(final String code,final String name) {
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
