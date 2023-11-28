package com.tkp.learn.web.model.vo;

/**
 * @Description:
 * @ClassName: LoginResult
 * @author: itw_wangshuai01
 * @date: 2019/8/7
 */
public class LoginResult {

    private String jwtToken;

    private String identity;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
