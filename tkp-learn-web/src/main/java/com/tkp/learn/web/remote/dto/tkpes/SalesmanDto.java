package com.tkp.learn.web.remote.dto.tkpes;

import lombok.ToString;

/**
 * 泰易销用户
 */

@ToString
public class SalesmanDto {

    /**
     * 业务员工号
     */
    private String saleNo;

    /**
     * 业务员姓名
     */
    private String name;

    /**
     * 业务员姓名
     */
    private int sex;
    /**
     * 机构编码：
     * 01 养老业务员->泰康养老机构；
     * 02  人寿个险业务员->泰康人寿机构；
     * 03 中介渠道外部销售人员 ->中介机构
     * 04 银行渠道外部销售人员 ->银行
     */
    private String managecom;

    /**
     * 业务员手机号
     */
    private String mobile;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 分公司代码
     */
    private String branchCom2Code;

    /**
     * 分公司名称
     */
    private String branchCom2Name;

    /**
     * 支公司代码
     */
    private String branchCom4Code;

    /**
     * 支公司名称
     */
    private String branchCom4Name;

    /**
     * 用户身份
     */
    private TKPESIdentityEnum userIdentity;

    /**
     * 头像地址
     */
    private String headImgUrl;

    /**
     * 内勤用户信息
     */
    private EmployeeDto employeeDto;

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getManagecom() {
        return managecom;
    }

    public void setManagecom(String managecom) {
        this.managecom = managecom;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranchCom2Code() {
        return branchCom2Code;
    }

    public void setBranchCom2Code(String branchCom2Code) {
        this.branchCom2Code = branchCom2Code;
    }

    public String getBranchCom2Name() {
        return branchCom2Name;
    }

    public void setBranchCom2Name(String branchCom2Name) {
        this.branchCom2Name = branchCom2Name;
    }

    public String getBranchCom4Code() {
        return branchCom4Code;
    }

    public void setBranchCom4Code(String branchCom4Code) {
        this.branchCom4Code = branchCom4Code;
    }

    public String getBranchCom4Name() {
        return branchCom4Name;
    }

    public void setBranchCom4Name(String branchCom4Name) {
        this.branchCom4Name = branchCom4Name;
    }

    public TKPESIdentityEnum getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(TKPESIdentityEnum userIdentity) {
        this.userIdentity = userIdentity;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
}
