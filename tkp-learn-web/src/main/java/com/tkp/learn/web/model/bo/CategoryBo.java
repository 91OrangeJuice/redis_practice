package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-10 19:15
 * description:
 **/

@Setter
@Getter
@ToString
public class CategoryBo {
    private String categoryCode;
    private String categoryName;
    private String iconPath;
    private int multiSelect;
    private int sortNo;
}
