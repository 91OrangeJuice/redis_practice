package com.tkp.learn.web.model.vo;


import javax.validation.Valid;
import java.util.List;

public class LiveEndVideoDataVo {
    private int pv;
    private int uv;
    @Valid
    private WebActivityVo webinar;
    private List<UsersOneMinuteVo> cc_users;
    private String unique_id;

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public WebActivityVo getWebinar() {
        return webinar;
    }

    public void setWebinar(WebActivityVo webinar) {
        this.webinar = webinar;
    }

    public List<UsersOneMinuteVo> getCc_users() {
        return cc_users;
    }

    public void setCc_users(List<UsersOneMinuteVo> cc_users) {
        this.cc_users = cc_users;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }
}
