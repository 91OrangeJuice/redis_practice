package com.tkp.learn.web.model.vo;

import java.io.Serializable;
import java.util.List;
public class PageParamVo<T> implements Serializable {

    /**
     * 页码
     */
    private int pageNum = 0;

    /**
     * 每页条数
     */
    private int pageSize = 10;

    /**
     * 查询条件
     */
    private T condition;

    /**
     * 排序
     */
    private List<OrderVo> orders;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    public List<OrderVo> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderVo> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "PageParamVo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", condition=" + condition +
                ", orders=" + orders +
                '}';
    }
}
