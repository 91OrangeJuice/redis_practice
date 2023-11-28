package com.tkp.learn.pushcdn.task.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;


/**
 * Created by t-wangrui01 on 2016/2/3.
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil(){
        throw new IllegalStateException("Utility class");
    }

    public static String parseObjToJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,true);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        StringWriter writer =new StringWriter();
        try {
            objectMapper.writeValue(writer,obj);
            return writer.toString();
        } catch (IOException e) {
            logger.debug("Obj To Json错误：",e);
            return "";
        }
    }

    public static <T> T parseJsonToObj(String json,Class<T> clazz) throws IOException {
        T returnObj ;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        returnObj = objectMapper.readValue(json,clazz);
        return returnObj;
    }

    public static String writeValueAsString(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("json 格式化异常：{}, e:{}", obj, e.getMessage());
        }
        return obj.toString();
    }
}
