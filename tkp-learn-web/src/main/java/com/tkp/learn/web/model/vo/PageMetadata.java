package com.tkp.learn.web.model.vo;

import lombok.ToString;

@ToString
public class PageMetadata {

    /**
     * 每页多少条
     */
    private long size;

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

    public PageMetadata() {

    }

    public PageMetadata(long size, long totalElements, long totalPages, long currentPage) {
        super();
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }
}
