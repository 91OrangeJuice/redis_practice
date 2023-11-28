package com.tkp.learn.archive.task.model.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/4
 * @version: 1.0
 */
@Getter
@Setter
@ToString
public class TaskTopListPo {
    private String saleNo;
    private int duration;
    private String lastStopAt;
    private int year;
    private int month;
    private int day;
}
