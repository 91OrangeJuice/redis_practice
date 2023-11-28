package com.tkp.learn.archive.task.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/24
 * @version: 1.0
 */
@Setter
@Getter
@TableName("video_behavior_his")
public class VideoBehaviorHisPo {

    @TableId
    @TableField(value = "id")
    private String id;
    @TableField("salesman_id")
    private String salesmanId;
    @TableField("duration")
    private int duration;
    @TableField("first_play_at")
    private LocalDateTime firstPlayAt;
    @TableField("last_stop_at")
    private LocalDateTime lastStopAt;
    @TableField("first_visit_at")
    private LocalDateTime firstVisitAt;
    @TableField("last_visit_at")
    private LocalDateTime lastVisitAt;
    @TableField("last_visit_city")
    private String lastVisitCity;
    @TableField("last_visit_ip")
    private String lastVisitIp;
    @TableField("last_visit_platform")
    private String lastVisitPlatform;
    @TableField("column_id")
    private String columnId;
    @TableField("lesson_id")
    private String lessonId;
    @TableField("retry_times")
    private int retryTimes;
    @TableField("salesman_status")
    private String salesmanStatus;
    @TableField("error_message")
    private String errorMessage;
    @TableField("create_time")
    private LocalDateTime createTime;
    @TableField("clue_id")
    private String clueId;
    @TableField("receive_time")
    private LocalDateTime receiveTime;
}


