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
 * date: 2020-06-05 16:06
 * description: 标签
 **/
@Setter
@Getter
@ToString
@TableName(value = "learn_def_category_tag")
public class LearnDefCategoryTagPo {

    @TableId
    @TableField(value = "code")
    private String code;

    @TableField(value = "name")
    private String name;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "category_code")
    private String categoryCode;

    @TableField(value = "config_id")
    private String configId;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(value = "creator")
    private String creator;

    @TableField(value = "updater")
    private String updater;
}
