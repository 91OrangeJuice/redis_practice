package com.tkp.learn.web.model.bo;

import com.tkp.learn.web.model.enums.IdentityEnum;
import lombok.ToString;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */

@ToString
public class LoginUser {

    /**
     * 工号
     */
    private String workNo;

    /**
     * 身份
     */
    private IdentityEnum identity;

    /**
     * 姓名
     */
    private String name;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    private String headImgUrl;

    private String orgShortName;

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public IdentityEnum getIdentity() {
        return identity;
    }

    public void setIdentity(IdentityEnum identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
}
