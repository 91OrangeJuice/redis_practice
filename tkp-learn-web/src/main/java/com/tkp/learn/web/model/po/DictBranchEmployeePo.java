package com.tkp.learn.web.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-08 09:32
 * description: 内勤机构码表
 **/
@Setter
@Getter
@ToString
@TableName(value = "dict_branch_employee")
public class DictBranchEmployeePo {

    @TableId
    @TableField(value = "id")
    private String id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "short_name")
    private String shortName;

    @TableField(value = "level")
    private String level;
}
