package com.tkp.learn.web.model.vo.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/8
 * @version: 1.0
 */
@ToString
@Getter
@Setter
public class PageVo<E> {
    /**
     * 数据内容
     */
    private List<E> datas;
    /**
     * 每页多少条
     */
    private long pageSize;

    /**
     * 总记录数
     */
    private long totalElements;

    /**
     * 总页数
     */
    private long totalPages;

    /**
     * 当前页 从0开始
     */
    private long currentPage;

}
