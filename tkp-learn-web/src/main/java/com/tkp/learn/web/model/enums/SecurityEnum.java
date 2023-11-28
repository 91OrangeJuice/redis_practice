package com.tkp.learn.web.model.enums;

import lombok.Getter;

/**
 * User: itw_wangchen02
 * Date: 2019/5/29 14:37
 * Description: 文件的安全级别枚举类
 */
@Getter
public enum SecurityEnum {
    LOGIN_SHARE("login_share","需要登录可转发"),
    ALL_SHARE("all_share","所有人可看可转发"),
    LOGIN_NO_SHARE("login_noshare","登录不可转发"),
    ALL_NO_SHARE("all_noshare","所有人可看不可转发"),
    ;
    private String code;
    private String message;

    SecurityEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
