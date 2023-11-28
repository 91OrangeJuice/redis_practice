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
 * @createDate: 2020/6/3
 * @version: 1.0
 */
@Setter
@Getter
@ToString
@TableName(value = "learn_video_year_top_list")
public class LearnVideoYearTopListPo {
    @TableId
    @TableField(value = "id")
    private String id;
    @TableField(value = "sale_no")
    private String saleNo ;
    @TableField(value = "duration")
    private int duration;
    @TableField(value = "duration_unit")
    private String durationUnit ;
    @TableField(value = "ranking")
    private int ranking ;
    @TableField(value = "update_time")
    private Date updateTime ;
    @TableField(value = "year")
    private int year;

    private String name;
    private String shortName;
    private String headImgUrl;
}
