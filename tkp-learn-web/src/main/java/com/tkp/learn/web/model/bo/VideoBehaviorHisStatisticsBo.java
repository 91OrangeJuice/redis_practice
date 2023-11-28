package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/1
 * @version: 1.0
 */
@ToString
@Getter
@Setter
public class VideoBehaviorHisStatisticsBo {

    private String userId;
    private int monthDuration;
    private int yearDuration;
    private int totalDuration;

}
