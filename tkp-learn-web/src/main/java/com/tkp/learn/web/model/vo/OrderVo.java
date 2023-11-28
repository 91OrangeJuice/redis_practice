package com.tkp.learn.web.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
