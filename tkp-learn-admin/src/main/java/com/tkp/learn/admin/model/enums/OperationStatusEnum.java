package com.tkp.learn.admin.model.enums;

/**
 * author: itw_wangsc01
 * date: 2020-07-24 16:32
 * description:
 **/

public enum OperationStatusEnum {

    SAVE("SAVE", "已保存"),
    COMMIT("COMMIT", "已提交"),
    REJECT("REJECT", "已驳回"),
    RECOVERED("RECOVERED", "已修复"),
    COMPLETED("COMPLETED", "已完成无需修复"),
    FAILED("FAILED","修复失败");


    private String code;
    private String name;

    OperationStatusEnum(String code, String name) {
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
