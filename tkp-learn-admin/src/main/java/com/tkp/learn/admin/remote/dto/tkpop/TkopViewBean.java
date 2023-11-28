package com.tkp.learn.admin.remote.dto.tkpop;

import lombok.Data;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/28
 * @version: 1.0
 */
@Data
public class TkopViewBean<T> {
    private int code;
    private String message;
    private T data;
}
