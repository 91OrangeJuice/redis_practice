package com.tkp.learn.web.model.enums;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/2
 * @version: 1.0
 */
public enum StudyStatusEnum {
    LEARNING("learning", 1),
    LEARNED("learned", 2),
    UNLEARN("unlearn", 0);

    private String code;
    private int name;

    StudyStatusEnum(final String code, final int name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public int getName() {
        return name;
    }
}
