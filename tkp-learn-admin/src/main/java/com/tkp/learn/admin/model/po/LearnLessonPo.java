package com.tkp.learn.admin.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@TableName( "learn_lesson")
public class LearnLessonPo {

    @TableId
    @TableField("uuid")
    private String uuid;

    @TableField("name")
    private String name;

    private String securityCode;

    @TableField("url")
    private String url;

    @TableField("type")
    private String type;

    @TableField("description")
    private String description;

    @TableField("size")
    private int size;

    @TableField("ico_hot_path")
    private String icoHotPath;

    @TableField("ico_recommend_path")
    private String icoRecommendPath;

    @TableField("ico_newest_path")
    private String icoNewestPath;

    @TableField("ico_share_path")
    private String icoSharePath;

    @TableField("file_status")
    private String fileStatus;

    @TableField("begin_time")
    private LocalDateTime beginTime;

    @TableField("end_time")
    private LocalDateTime endTime;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("live_begin_time")
    private LocalDateTime liveBeginTime;

    @TableField("live_end_time")
    private LocalDateTime liveEndTime;

}
