package com.tkp.learn.web.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-11 17:14
 * description:
 **/

@Getter
@Setter
@ToString
public class BannerVo {
    private String bannerCode;
    private String bannerName;
    private String iconPath;
    private int sortNo;
}
