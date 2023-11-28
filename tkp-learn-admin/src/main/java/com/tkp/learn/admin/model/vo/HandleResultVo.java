package com.tkp.learn.admin.model.vo;

import lombok.Data;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/31
 * @version: 1.0
 */
@Data
public class HandleResultVo {
    private int successCount;
    private int errorCount;
    private int total;
}
