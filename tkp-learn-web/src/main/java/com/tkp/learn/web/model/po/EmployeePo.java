package com.tkp.learn.web.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.ToString;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */

@ToString
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getOaNo() {
        return oaNo;
    }

    public void setOaNo(String oaNo) {
        this.oaNo = oaNo;
    }

    public String getLeaderEmpNo() {
        return leaderEmpNo;
    }

    public void setLeaderEmpNo(String leaderEmpNo) {
        this.leaderEmpNo = leaderEmpNo;
    }

    public String getLevel1DeptId() {
        return level1DeptId;
    }

    public void setLevel1DeptId(String level1DeptId) {
        this.level1DeptId = level1DeptId;
    }

    public String getLevel1DeptName() {
        return level1DeptName;
    }

    public void setLevel1DeptName(String level1DeptName) {
        this.level1DeptName = level1DeptName;
    }

    public String getLevel2DeptId() {
        return level2DeptId;
    }

    public void setLevel2DeptId(String level2DeptId) {
        this.level2DeptId = level2DeptId;
    }

    public String getLevel2DeptName() {
        return level2DeptName;
    }

    public void setLevel2DeptName(String level2DeptName) {
        this.level2DeptName = level2DeptName;
    }

    public String getLevel3DeptId() {
        return level3DeptId;
    }

    public void setLevel3DeptId(String level3DeptId) {
        this.level3DeptId = level3DeptId;
    }

    public String getLevel3DeptName() {
        return level3DeptName;
    }

    public void setLevel3DeptName(String level3DeptName) {
        this.level3DeptName = level3DeptName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
