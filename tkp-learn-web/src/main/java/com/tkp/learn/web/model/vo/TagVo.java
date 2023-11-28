package com.tkp.learn.web.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-05 19:53
 * description:
 **/
@Getter
@Setter
@ToString
public class TagVo {
    private String tagCode;
    private String tagName;
    private String iconPath;
    private int sortNo;
}
