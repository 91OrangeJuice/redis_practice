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
 * date: 2020-06-05 10:43
 * description:
 **/
@Getter
@Setter
@ToString
@TableName(value = "learn_def_category")
public class LearnDefCatgoryPo {
    @TableId
    @TableField(value = "code")
    private String code;
    @TableField(value = "name")
    private String name;
    @TableField(value = "upperId")
    private String upperId;
    @TableField(value = "icon")
    private String icon;
    @TableField(value = "multi_select")
    private int multiSelect;
    @TableField(value = "display")
    private int display;
    @TableField(value = "order_no")
    private int orderNo;
    @TableField(value = "creator")
    private String creator;
    @TableField(value = "updater")
    private String updater;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

}
