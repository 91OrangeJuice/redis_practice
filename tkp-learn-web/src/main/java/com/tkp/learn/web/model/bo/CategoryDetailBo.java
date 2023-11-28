package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-11 19:42
 * description:
 **/
@Getter
@Setter
@ToString
public class CategoryDetailBo {
    private String categoryCode;
    private String categoryName;
    private String iconPath;
    private String upperCategoryCode;
    private int level;
    private int multiSelect;
    private int sortNo;
}
