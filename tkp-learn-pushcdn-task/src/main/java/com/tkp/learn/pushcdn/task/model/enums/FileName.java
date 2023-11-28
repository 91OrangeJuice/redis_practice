package com.tkp.learn.pushcdn.task.model.enums;

/**
 * Created by itw_wangshuai01 on 2020/6/15.
 */
public enum FileName {

    HOT_LESSONS("hotLessons", "热门课程"),
    MENU("menus", "菜单列表");

    private String code;

    private String name;

    FileName(final String code, final String name) {
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
