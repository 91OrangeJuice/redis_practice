package com.tkp.learn.admin.model.enums;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/29
 * @version: 1.0
 */
public enum ErrorMessageEnum {


    NONEUSER("NONEUSER", "没有匹配到人员;"),
    NONENAME("NONENAME","姓名为空;"),
    NONECOMPANY("NONECOMPANY","公司为空;"),
    NOPERMISSION("NOPERMISSION","没有该人员的修复权限;"),
    COMPANYNOTMATCH("COMPANYNOTMATCH","公司与当前人不匹配;"),
    NAMENOTMATCH("NAMENOTMATCH","姓名与当前员工号不匹配;"),
    COMMITSUCCESS("COMMITSUCCESS","提交成功"),
    REJECTSUCCESS("REJECTSUCCESS","驳回成功"),
    NONEDATA("NONEDATA","请求参数有误"),
    NONELEARNDATE("NONELEARNDATE","学习完成时间为空"),
    NOMATCHLESSON("NOMATCHLESSON","没有匹配到课程");



    private String code;
    private String name;

    ErrorMessageEnum(String code, String name) {
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
