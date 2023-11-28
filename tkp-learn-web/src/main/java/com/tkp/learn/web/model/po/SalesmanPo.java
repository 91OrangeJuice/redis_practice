package com.tkp.learn.web.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */

@ToString
@TableName(value = "salesman")
public class SalesmanPo {

    @TableId
    @TableField(value = "uuid")
    private String uuid;

    @TableField(value = "name")
    private String name;

    @TableField(value = "sale_no")
    private String saleNo;

    @TableField(value = "branch_code")
    private String branchCode;

    @TableField(value = "branch_name")
    private String branchName;

    @TableField(value = "branch_son_code")
    private String branchSonCode;

    @TableField(value = "branch_son_name")
    private String branchSonName;

    @TableField(value = "id_no")
    private String idNo;

    @TableField(value = "mobile")
    private String mobile;

    @TableField(value = "rank_channel_code")
    private String rankChannelCode;

    @TableField(value = "rank_channel_name")
    private String rankChannelName;

    @TableField(value = "staff_code")
    private String staffCode;

    @TableField(value = "staff_name")
    private String staffName;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchSonCode() {
        return branchSonCode;
    }

    public void setBranchSonCode(String branchSonCode) {
        this.branchSonCode = branchSonCode;
    }

    public String getBranchSonName() {
        return branchSonName;
    }

    public void setBranchSonName(String branchSonName) {
        this.branchSonName = branchSonName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRankChannelCode() {
        return rankChannelCode;
    }

    public void setRankChannelCode(String rankChannelCode) {
        this.rankChannelCode = rankChannelCode;
    }

    public String getRankChannelName() {
        return rankChannelName;
    }

    public void setRankChannelName(String rankChannelName) {
        this.rankChannelName = rankChannelName;
    }

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
