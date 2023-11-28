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
@TableName(value = "employee_video_behavior_history")
public class EmployeeVideoBehaviorHisPo {

    @TableId
    @TableField(value = "uuid")
    private String uuid;
    @TableField(value = "oa_no")
    private String oaNo ;
    @TableField(value = "lesson_id")
    private String lessonId ;
    @TableField(value = "duration")
    private int duration;
    @TableField(value = "first_play_at")
    private Date firstPlayAt ;
    @TableField(value = "last_stop_at")
    private Date lastStopAt;
    @TableField(value = "first_visit_at")
    private Date firstVisitAt;
    @TableField(value = "last_visit_at")
    private Date lastVisitAt ;
    @TableField(value = "column_id")
    private String columnId ;
    @TableField(value = "clue_id")
    private String clueId ;
    @TableField(value = "last_visit_city")
    private String lastVisitCity ;
    @TableField(value = "last_visit_ip")
    private String lastVisitIp ;
    @TableField(value = "last_visit_platform")
    private String lastVisitPlatform ;
    @TableField(value = "create_time")
    private Date createTime ;
    @TableField(value = "receive_time")
    private Date receiveTime;
    @TableField(value = "try_times")
    private int tryTimes ;

}
