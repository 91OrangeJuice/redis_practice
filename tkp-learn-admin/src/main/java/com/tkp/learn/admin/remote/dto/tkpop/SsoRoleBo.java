package com.tkp.learn.admin.remote.dto.tkpop;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * author: itw_lixg01
 * Date: 2020/1/19 17:11
 * Description：泰运营登录用户的角色实体类
 */
@Getter
@Setter
public class SsoRoleBo implements Serializable {
    private static final long serialVersionUID = 2234718910825560985L;
    private String roleId;
    private String roleName;

    @Override
    public String toString() {
        return "{" +
                "roleId:" + roleId + ", " +
                "roleName:" + roleName +
                "}";
    }
}
