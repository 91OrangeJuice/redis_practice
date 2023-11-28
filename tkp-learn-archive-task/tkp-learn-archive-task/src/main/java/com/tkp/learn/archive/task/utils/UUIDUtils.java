package com.tkp.learn.archive.task.utils;

import java.util.UUID;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/5
 * @version: 1.0
 */
public class UUIDUtils {
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
