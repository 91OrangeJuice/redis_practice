package com.tkp.learn.web.remote.dto.tkpes;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */
public enum TKPESIdentityEnum {

    OA("OA", "内勤"),
    SALE("SALE","外勤");

    private String code;

    private String name;

    TKPESIdentityEnum(final String code, final String name) {
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
