package com.tkp.learn.web.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-02 18:30
 * description: 当前登录用户
 **/
@Getter
@Setter
@ToString
public class UserVo {
    private String userCode;
    private String userName;
    private String orgShortName;
    private String iconPath;
    private int praiseCount;
    private int monthDuration;
    private int totalDuration;
}
