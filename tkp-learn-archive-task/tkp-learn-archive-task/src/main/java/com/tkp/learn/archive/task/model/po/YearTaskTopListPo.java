package com.tkp.learn.archive.task.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description: 年榜月榜PO
 * @author: itw_wangsc01
 * @createDate: 2020/6/4
 * @version: 1.0
 */
@Getter
@Setter
public class YearTaskTopListPo {
    private String id ;
    private String saleNo;
    private long duration ;
    private String durationUnit;
    private int ranking;
    private Date updateTime;
    private int year ;
}
