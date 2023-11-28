package com.tkp.learn.web.model.enums;

import lombok.Getter;

/**
 * author: itw_lixg01
 * date: 2020-06-05 17:18
 * description: 标签类型
 **/
@Getter
public enum TagTypeEnum {

    HOME_TOP("HOME_TOP", "首页顶部"),
    PROJECT_FLOW("PROJECT_FLOW", "项目流程"),
    LESSON_LIST_TOP("LESSON_LIST_TOP", "课程列表顶部"),
    FILTER("FILTER", "筛选");

    private String code;
    private String name;

    TagTypeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }
}
