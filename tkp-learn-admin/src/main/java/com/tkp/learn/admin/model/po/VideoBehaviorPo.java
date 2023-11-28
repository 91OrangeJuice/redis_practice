package com.tkp.learn.admin.model.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/31
 * @version: 1.0
 */
@Data
public class VideoBehaviorPo {

    private String id;
    private String salesmanId;
    private String  lessonId;
    private int duration;
    private LocalDateTime firstPlayAt;
    private LocalDateTime lastStopAt;
    private LocalDateTime firstVisitAt;
    private LocalDateTime lastVisitAt;
    private LocalDateTime createTime;
    private LocalDateTime  receiveTime;
    private String  columnId;
    private String clueId;
    private String  lastVisitCity;
    private String lastVisitIp;
    private String  lastVisitPlatform;
    private int retryTimes;
    private String  errorMessage;

}
