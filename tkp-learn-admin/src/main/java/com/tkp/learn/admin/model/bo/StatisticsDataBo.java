package com.tkp.learn.admin.model.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/31
 * @version: 1.0
 */
@Data
public class StatisticsDataBo {

    private String lessonId;
    private String userId;
    private int duration;
    private double percentage;
    private String status;
    private LocalDateTime lastTime;

}
