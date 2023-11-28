package com.tkp.learn.web.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @description: 我的学习历史列表
 * @author: itw_wangsc01
 * @createDate: 2020/5/26
 * @version: 1.0
 */
@Getter
@Setter
@ToString
public class MyLearningHisVo {
    /**
     * 课程名
     */
    private String name;
    /**
     * 课程介绍
     */
    private String description;
    /**
     * 标签列表
     */
    private List<String> tags;
    /**
     * 课程完成状态 0：学习中，1：已完成
     */
    private int learnStatus;
    /**
     * 学习时长（秒）
     */
    private int studyTime;
    /**
     * 最后学习日期
     */
    private Date LastStudyDate;
    /**
     * 课程图片地址
     */
    private String iconPath;

}
