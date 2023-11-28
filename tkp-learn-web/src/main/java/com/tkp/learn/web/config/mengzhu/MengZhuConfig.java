package com.tkp.learn.web.config.mengzhu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by itw_wangshuai01 on 2020/4/23.
 */

@Configuration
public class MengZhuConfig {

    @Value("${config.mengzhu.systemCode}")
    private String systemCode;

    @Value("${config.mengzhu.businessCode}")
    private String businessCode;

    @Value("${config.mengzhu.appid}")
    private String appid;

    @Value("${config.mengzhu.secret}")
    private String secret;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
}
