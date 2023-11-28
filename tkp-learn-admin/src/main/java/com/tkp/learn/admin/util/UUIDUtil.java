package com.tkp.learn.admin.util;

import java.util.UUID;

/**
 * Created by wangrui67 on 2016/3/18.
 * 生成UUID
 */
public class UUIDUtil {
    //生成UUID
    public static String generatedUUID(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

}
