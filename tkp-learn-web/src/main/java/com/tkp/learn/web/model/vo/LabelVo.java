package com.tkp.learn.web.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-12 11:04
 * description:
 **/

@Setter
@Getter
@ToString
public class LabelVo {
    private String code;
    private String name;
    private int sortNo;
}
