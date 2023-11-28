package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-16 09:50
 * description:
 **/

@Getter
@Setter
@ToString
public class MenuBo {

    private String menuCode;

    private String menuName;

    private String upperCode;

    private String iconPath;

    private int level;

    private int sortNo;
}
