package com.tkp.learn.web.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-12 15:43
 * description:
 **/

@Getter
@Setter
@ToString
@TableName(value = "learn_menu")
public class MenuPo {

    @TableId
    @TableField(value = "code")
    private String code;

    @TableField(value = "name")
    private String name;

    @TableField(value = "upperCode")
    private String upperCode;

    @TableField(value = "target_type")
    private String targetType;

    @TableField(value = "targetUrl")
    private String targetUrl;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "order_no")
    private int orderNo;
}
