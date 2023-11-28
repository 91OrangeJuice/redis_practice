package com.tkp.learn.pushcdn.task.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by itw_wangshuai01 on 2020/6/9.
 */

@Configuration
public class CDNConfig {

    //文件在CDN上保存的根目录
    @Value("${config.cdn.host}")
    private String savePath;

    //文件相对路径
    @Value("${config.cdn.relativePath}")
    private String relativePath;

    //文件来源（TX 弹性平台 TK 商城  WX 微信组）
    @Value("${config.cdn.source}")
    private String source;

    //上传至CDN的图片默认格式
    @Value("${config.cdn.imgProperty}")
    private String imgProperty;

    //是否覆盖原文件
    @Value("${config.cdn.isCover}")
    private boolean isCover;

    //文件访问路径域名
    @Value("${config.cdn.domain}")
    private String domain;

    //文件默认封面图地址
    @Value("${config.cdn.imgPathDefault}")
    private String imgPathDefault;

    //单个文件最大限制
    @Value("${config.cdn.maxSize}")
    private long maxSize;

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImgProperty() {
        return imgProperty;
    }

    public void setImgProperty(String imgProperty) {
        this.imgProperty = imgProperty;
    }

    public boolean isCover() {
        return isCover;
    }

    public void setCover(boolean cover) {
        isCover = cover;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getImgPathDefault() {
        return imgPathDefault;
    }

    public void setImgPathDefault(String imgPathDefault) {
        this.imgPathDefault = imgPathDefault;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }
}
