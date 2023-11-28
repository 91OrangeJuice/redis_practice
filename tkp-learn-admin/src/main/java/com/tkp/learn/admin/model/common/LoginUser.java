package com.tkp.learn.admin.model.common;

import com.tkp.learn.admin.model.enums.IdentityEnum;
import lombok.Data;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */
@Data
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

    private String authorization;

    private String roleName;

    private String roleId;
}
