package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/3
 * @version: 1.0
 */
@Getter
@Setter
@ToString
public class LearnVideoTopListBo {

    private String id;
    private String saleNo ;
    private int duration;
    private String durationUnit ;
    private int ranking ;
    private Date updateTime ;
    private int year;
    private int month ;

}
