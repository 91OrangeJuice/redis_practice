package com.tkp.learn.admin.model.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/27
 * @version: 1.0
 */
@Data
public class EmployeeLessonMidPo {
    private String uuid ;
    private String lessonId;
    private String oaNo;
    private int learnedDuration ;
    private double percentage ;
    private String state;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private LocalDateTime finishTime;
    private LocalDateTime beginTime ;
    private LocalDateTime latestLearnedTime;
}
