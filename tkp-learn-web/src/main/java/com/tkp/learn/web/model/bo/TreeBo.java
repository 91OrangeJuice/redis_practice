package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-11 20:09
 * description:
 **/

@Setter
@Getter
@ToString
public class TreeBo {
    private String code;
    private String name;
    private String upperCode;
    private int level;
    private String iconPath;
    private int multiSelect;
    private int sortNo;
    private List<TreeBo> children;
}
