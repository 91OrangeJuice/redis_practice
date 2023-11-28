package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-05 15:59
 * description: 标签
 **/
@Getter
@Setter
@ToString
public class TagBo {
    private String tagCode;
    private String tagName;
    private int sortNo;
}
