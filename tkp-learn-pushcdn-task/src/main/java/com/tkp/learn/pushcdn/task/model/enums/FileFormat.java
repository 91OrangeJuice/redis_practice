package com.tkp.learn.pushcdn.task.model.enums;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */
public enum FileFormat {

    JSON("json", "json文件");

    private String code;

    private String name;

    FileFormat(final String code, final String name) {
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
