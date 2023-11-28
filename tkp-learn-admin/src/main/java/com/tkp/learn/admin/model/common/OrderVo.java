package com.tkp.learn.admin.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderVo {

    /**
     * 排序字段
     */
    private String columnName;

    /**
     * 顺序
     */
    private String sort;
}
