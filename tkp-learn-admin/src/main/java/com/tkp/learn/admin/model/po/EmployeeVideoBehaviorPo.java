package com.tkp.learn.admin.model.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/8/3
 * @version: 1.0
 */
@Data
public class EmployeeVideoBehaviorPo {
    private String uuid;
    private String oaNo;
    private String columnId;
    private LocalDateTime createTime;
    private int tryTimes;
    private int duration;
    private String lessonId;
    private LocalDateTime firstPlayAt;
    private LocalDateTime lastStopAt;
    private LocalDateTime receiveTime;
    private String errorMessage;
}
