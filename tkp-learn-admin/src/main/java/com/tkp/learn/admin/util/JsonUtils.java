package com.tkp.learn.admin.util;

import com.google.gson.Gson;
import org.springframework.util.ObjectUtils;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/30
 * @version: 1.0
 */
public class JsonUtils {

    private static Gson json;

    public static Gson getGson(){
        if(ObjectUtils.isEmpty(json)){
            json=new Gson();
        }
        return json;
    }


}
