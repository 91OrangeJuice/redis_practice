package com.tkp.learn.web.model.conts;

import java.time.format.DateTimeFormatter;

/**
 * Created by itw_wangshuai01 on 2020/6/9.
 */
public class Constant {

    //文件所在的上级目录名称
    public static final DateTimeFormatter PATH_FMT = DateTimeFormatter.ofPattern("yyyy-MM/");

    public static final String CS_POOL = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String T_ID = "t_id";
    public static final String SYSTEM_CODE = "system_code";
    public static final String BUSINESS_CODE = "business_code";
    public static final String SHARE_ID_KEY = "share_id";
    public static final String EXTEND_FILED_KEY = "t_ext";
    public static final String SIGNED_AT_KEY = "signed_at";
    public static final String CLUE_ID_KEY = "clueId";
    public static final String SIGN_KEY = "sign";
    public static final String APPID = "appid";

}
