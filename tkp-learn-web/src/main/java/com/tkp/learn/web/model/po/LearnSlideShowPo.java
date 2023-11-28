package com.tkp.learn.web.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * author: itw_lixg01
 * date: 2020-06-11 17:48
 * description:
 **/
@Setter
@Getter
@ToString
@TableName(value = "learn_slide_show")
public class LearnSlideShowPo {

    @TableId
    @TableField(value = "code")
    private String code;
    @TableField(value = "name")
    private String name;

    @TableField(value = "lesson_id")
    private String lessonId;

    @TableField(value = "target_type")
    private String targetType;
    @TableField(value = "target_url")
    private String targetUrl;
    @TableField(value = "img_path")
    private String imgPath;
    @TableField(value = "display")
    private int display;
    @TableField(value = "order_no")
    private int orderNo;
    @TableField(value = "begin_time")
    private LocalDateTime beginTime;
    @TableField(value = "end_time")
    private LocalDateTime endTime;
}
