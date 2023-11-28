package com.tkp.learn.web.model.enums;

import lombok.Getter;

/**
 * author: itw_lixg01
 * date: 2020-06-09 18:43
 * description:
 **/
@Getter
public enum EmployeeLearnStatusEnum {

    UNLEARN(0, "未学习"),
    LEARNING(1, "学习中"),
    LEARNED(2, "已完成");

    private int code;
    private String name;

    EmployeeLearnStatusEnum(final int code, final String name) {
        this.code = code;
        this.name = name;
    }
}
