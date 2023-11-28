package com.tkp.learn.admin.model.enums;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/30
 * @version: 1.0
 */
public enum RoleEnum {

    SUBMITTER("RL10010", "易学习数据提交员"),
    APPROVER("RL10011","易学习审核员");

    private String code;

    private String name;

    RoleEnum(final String code, final String name) {
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
