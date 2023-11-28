package com.tkp.learn.admin.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */

@Data
@TableName(value = "employee")
public class EmployeePo {

    @TableId
    @TableField(value = "uuid")
    private String uuid;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "emp_no")
    private String empNo;

    @TableField(value = "oa_no")
    private String oaNo;

    @TableField(value = "leader_emp_no")
    private String leaderEmpNo;

    @TableField(value = "level1_dept_id")
    private String level1DeptId;

    @TableField(value = "level1_dept_name")
    private String level1DeptName;

    @TableField(value = "level2_dept_id")
    private String level2DeptId;

    @TableField(value = "level2_dept_name")
    private String level2DeptName;

    @TableField(value = "level3_dept_id")
    private String level3DeptId;

    @TableField(value = "level3_dept_name")
    private String level3DeptName;

    @TableField(value = "state")
    private int state;

    private String ebaOrgCode1;

}
