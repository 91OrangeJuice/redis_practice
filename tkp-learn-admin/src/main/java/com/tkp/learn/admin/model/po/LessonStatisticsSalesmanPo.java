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
public class LessonStatisticsSalesmanPo {
    private String id ;
    private String lessonId;
    private String saleNo;
    private int learnedDuration ;
    private double percentage ;
    private String status ;
    private LocalDateTime beginTime ;
    private LocalDateTime finishTime;
    private LocalDateTime lastTime;
}
