package com.tkp.learn.web.model.bo;

import java.util.Map;

/**
 * Created by Timothy Han on 2019/9/25 10:51
 * Version 1.0
 */
public class UrlEntity {

    /**
     * 基础url
     */
    private String baseUrl;
    /**
     * url参数
     */
    private Map<String, String> params;

    @Override
    public String toString() {
        return "UrlEntity{" +
                "baseUrl='" + baseUrl + '\'' +
                ", params=" + params +
                '}';
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
