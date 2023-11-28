package com.tkp.learn.web.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/5/27
 * @version: 1.0
 */
@Getter
@Setter
@ToString
public class RankingListPage {

    /**
     * 当前页
     */
    private int pageNum;

    /**
     * 每页条数
     */
    private int pageSize;

    private int year;

    private int month;

}
