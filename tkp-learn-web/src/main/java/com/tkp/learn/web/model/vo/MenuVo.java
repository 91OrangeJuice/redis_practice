package com.tkp.learn.web.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-12 15:47
 * description:
 **/

@Getter
@Setter
@ToString
public class MenuVo {

    private String menuCode;

    private String menuName;

    private String iconPath;

    private int sortNo;
}
