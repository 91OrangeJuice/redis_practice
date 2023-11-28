package com.tkp.learn.web.remote.dto.tkpes;

import lombok.ToString;

/**
 * author: itw_lixg01
 * Date: 2020/3/11 17:05
 * Description：内勤用户信息
 */

@ToString
public class EmployeeDto {

    private String accountNo;

    private String nickName;

    private String deptCode;

    private String deptName;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
