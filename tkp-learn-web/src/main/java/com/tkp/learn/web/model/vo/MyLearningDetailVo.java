package com.tkp.learn.web.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: 我的学习详情实体
 * @author: itw_wangsc01
 * @createDate: 2020/5/26
 * @version: 1.0
 */
@Getter
@Setter
@ToString
public class MyLearningDetailVo {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 组织名称
     */
    private String orgName;
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 用户头像地址
     */
    private String iconPath;
    /**
     * 简称
     */
    private String orgShortName;
    /**
     * 本月学习时长（秒）
     */
    private int monthDuration;
    /**
     * 总学习时长（秒）
     */
    private int totalDuration;
    /**
     * 完成课程数
     */
    private int finishLessonCount;
    /**
     * 必修课程数
     */
    private int requiredLessonCount;
    /**
     * 必修完成课程数
     */
    private int requiredFinishLessonCount;
    /**
     * 总课程数
     */
    private int totalLessonCount;
    /**
     * 月度排名
     */
    private int monthRanking;
    /**
     * 年度排名
     */
    private int yearRanking;
    /**
     * 最新点赞数
     */
    private int praiseCount;


}
