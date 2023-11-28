package com.tkp.learn.archive.task.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/8
 * @version: 1.0
 */
@Setter
@Getter
public class LearnDataVideoDayPo {
    private String id ;
    private String saleNo;
    private long duration ;
    private int year ;
    private int month;
    private int dayOfMonth ;
    private Date createTime;
    private int ranking;
}
