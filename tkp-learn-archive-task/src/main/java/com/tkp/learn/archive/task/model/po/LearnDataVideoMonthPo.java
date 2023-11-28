package com.tkp.learn.archive.task.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/10
 * @version: 1.0
 */
@Getter
@Setter
public class LearnDataVideoMonthPo {
    private String id;
    private String saleNo;
    private int duration;
    private int year;
    private int month;
    private Date updateTime;
    private Date createTime;
}
