package com.tkp.learn.admin.model.common;

import lombok.ToString;

/**
 * @Description:
 * @ClassName: JwtResultBo
 * @author: itw_wangshuai01
 * @date: 2019/7/16
 */

@ToString
public class JwtResultBo {

    private String data;

    private String identity;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
