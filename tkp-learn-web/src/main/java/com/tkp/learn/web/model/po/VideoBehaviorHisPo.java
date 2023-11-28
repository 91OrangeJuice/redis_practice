package com.tkp.learn.web.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/1
 * @version: 1.0
 */
@ToString
@Getter
@Setter
@TableName(value = "video_behavior_his")
public class VideoBehaviorHisPo {

    @TableId
    @TableField(value = "id")
    private String id ;
    @TableField(value = "salesman_id")
    private String salesmanId;
    @TableField(value = "duration")
    private int duration ;
    @TableField(value = "first_play_at")
    private Date firstPlayAt;
    @TableField(value = "last_stop_at")
    private Date LastStopAt ;
    @TableField(value = "first_visit_at")
    private Date firstVisitAt ;
    @TableField(value = "last_visit_at")
    private Date lastVisitAt;
    @TableField(value = "last_visit_city")
    private String lastVisitCity;
    @TableField(value = "last_visit_ip")
    private String lastVisitIp;
    @TableField(value = "last_visit_platform")
    private String lastVisitPlatform;
    @TableField(value = "column_id")
    private String columnId;
    @TableField(value = "lesson_id")
    private String lessonId;
    @TableField(value = "retry_times")
    private int retryTimes;
    @TableField(value = "salesman_status")
    private String salesmanStatus;
    @TableField(value = "error_message")
    private String errorMessage;
    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "clue_id")
    private String clueId;
    @TableField(value = "receive_time")
    private Date receiveTime ;

    private int month;
    private int year;

}
