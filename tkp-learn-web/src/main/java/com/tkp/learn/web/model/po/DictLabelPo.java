package com.tkp.learn.web.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-12 10:49
 * description:
 **/
@Getter
@Setter
@ToString
@TableName(value = "dict_label")
public class DictLabelPo {

    @TableId
    @TableField(value = "id")
    private String id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "label_color")
    private String labelColor;

    @TableField(value = "sort_no")
    private int sortNo;
}
