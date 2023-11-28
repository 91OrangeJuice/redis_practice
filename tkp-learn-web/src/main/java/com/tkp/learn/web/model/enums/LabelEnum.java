package com.tkp.learn.web.model.enums;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/2
 * @version: 1.0
 */

public enum LabelEnum {

    REQUIRED("REQUIRED","必修"),
    KEY("KEY", "重要"),
    OPTIONAL("OPTIONAL","选修");

    private String code;
    private String name;

    LabelEnum(final String code,final String name) {
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
