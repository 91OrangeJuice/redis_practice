package com.tkp.learn.web.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: 排序实体
 * @author: itw_wangsc01
 * @createDate: 2020/5/27
 * @version: 1.0
 */
@Getter
@Setter
@ToString
public class RankingVo {
    /**
     * 排名人名称
     */
    private String userName;
    /**
     * 机构简称
     */
    private String orgShortName;
    /**
     * 排名序号
     */
    private int rank;
    /**
     * 学习时长
     */
    private int studyDuration;
    /**
     * 头像图标
     */
    private String iconPath;

}
