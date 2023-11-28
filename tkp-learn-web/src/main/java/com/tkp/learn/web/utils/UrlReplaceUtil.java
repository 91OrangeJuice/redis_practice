/*
 * Copyright (c) 2018 Taikang Pension. All rights reserved. Taikang Pension
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tkp.learn.web.utils;

import com.tkp.learn.web.model.bo.UrlEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 该工具类用于替换跳转地址URL的通配符<p>
 * Eg.http://www.baidu.com?openid=#DES[$(openid)]
 * <p>
 * Created by Timothy Han on 2018/9/18 13:57
 */
public class UrlReplaceUtil {

    private UrlReplaceUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlReplaceUtil.class);

    //替换功能·通配符左标
    private static final String REPLACE_LEFT_TAG = "$(";

    //替换功能·通配符右标
    private static final char REPLACE_RIGHT_TAG = ')';

    //AES加密
    private static final String AES = "AES";

    private static final String ENCRYPT_HEAD = "##";
    private static final char ENCRYPT_LEFT = '[';
    //被加密信息通配符右标
    private static final char ENCRYPT_RIGHT = ']';

    private static final String SEPARATOR = "\\.";

    private static final String SIGNATURE = "&sign=";

    private static final String SIGNATURE_TAG = "?";


    /**
     * 获取真正跳转url
     *
     * @param url     替换参数前的url
     * @param dataMap 替换参数map
     * @return 处理后的utl
     */
    public static String replaceVariables(String url, Map<String, Object> dataMap) {
        return replaceVariables(url, dataMap, null,false);
    }

    /**
     * 获取真正跳转url
     *
     * @param url     替换参数前的url
     * @param dataMap 替换参数map
     * @param key     参数加密时的秘钥（长度为16位的字符串）
     * @return 处理后的utl
     */
    public static String replaceVariables(String url, Map<String, Object> dataMap, String key) {
        return replaceVariablesWithSign(url, dataMap, key);
    }

    /**
     * 获取真正跳转url
     *
     * @param url         替换参数前的url
     * @param dataMap     替换参数map
     * @param isSignature 是否签名
     * @return 处理后的utl
     */
    public static String replaceVariables(String url, Map<String, Object> dataMap, String key, boolean isSignature) {
        url = replaceParams(url, dataMap);
        LOGGER.debug("替换参数后：{}", url);
        url = encryptParams(url, key);
        LOGGER.debug("编码后：{}", url);
        if (isSignature) {
            url = signature(url);
            LOGGER.debug("签名后：{}", url);
        }
        return url;
    }

    /**
     * 获取真正跳转url
     *
     * @param url     替换参数前的url
     * @param dataMap 替换参数map
     * @return 处理后的utl
     */
    public static String replaceVariablesWithSign(String url, Map<String, Object> dataMap, String key) {
        url = replaceParams(url, dataMap);
        LOGGER.debug("替换参数后：{}", url);
        url = encryptParams(url, key);
        LOGGER.debug("编码后：{}", url);
        url = signature(url);
        LOGGER.debug("签名后：{}", url);
        return url;
    }

    /**
     * 将url携带参数进行签名
     *
     * @param content 带参url
     * @return 签名后的url
     */
    public static String signature(String content) {
        int index = content.indexOf(SIGNATURE_TAG) + 1;
        if (index <= 0) {
            return content;
        }
        String needSignature = content.substring(index, content.length());
        LOGGER.debug("待签名串:{}", needSignature);
        String signature = SHA256EncryptionUtils.encryptionSHA256Base64UrlSafe(needSignature);

        return content + SIGNATURE + signature;
    }

    /**
     * 加密
     *
     * @param content 待加密串
     * @param key     秘钥
     * @return 加密后字符串
     */
    public static String encryptParams(String content, String key) {
        int leftIndex = 0;
        int rightIndex = 0;
        int length = 0;
        boolean isFoundEndTag = false;
        StringBuilder rtnContent;
        StringBuilder encryptMethod;
        leftIndex = content.indexOf(ENCRYPT_HEAD);
        if (leftIndex < 0) {
            return content;
        }

        length = content.length();
        rtnContent = new StringBuilder();
        encryptMethod = new StringBuilder();
        for (int i = (leftIndex + ENCRYPT_HEAD.length()); i < length; i++) {
            char c = content.charAt(i);
            if (c == ENCRYPT_LEFT) {
                rightIndex = i;
                isFoundEndTag = true;
                break;
            } else {
                encryptMethod.append(c);
            }
        }
        if (!isFoundEndTag) { //未找到闭合标签，抛出异常
            throw new RuntimeException("[" + content + "]加密错误," + AES + encryptMethod.toString() + "未找到结束标记");
        }
        //获取待加密字符串
        StringBuilder needEncryptParams = new StringBuilder();
        for (int i = content.indexOf(ENCRYPT_LEFT) + 1; i < length; i++) {
            char c = content.charAt(i);
            if (c == ENCRYPT_RIGHT) {
                rightIndex = i;
                break;
            } else {
                needEncryptParams.append(c);
            }
        }

        String encryptParams = encryptParams(needEncryptParams.toString(), encryptMethod.toString(), key);

        if (leftIndex > 0 && rightIndex < length - 1) {
            if (content.charAt(leftIndex - 1) == '"' && content.charAt(rightIndex + 1) == '"') {
                leftIndex = leftIndex - 1;
                rightIndex = rightIndex + 1;
            }
        }
        rtnContent.append(content.substring(0, leftIndex));
        rtnContent.append(encryptParams);
        rtnContent.append(encryptParams(content.substring(rightIndex + 1, length), key));

        return rtnContent.toString();

    }

    /**
     * 截取需要加密的字符串
     *
     * @param content url地址
     * @return
     */
    public static String subNeedEncryptParams(String content) {
        //获取待加密字符串
        StringBuilder needEncryptParams = new StringBuilder();
        for (int i = content.indexOf(ENCRYPT_LEFT) + 1; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == ENCRYPT_RIGHT) {
                break;
            } else {
                needEncryptParams.append(c);
            }
        }
        LOGGER.debug("截取的加密串：{}", needEncryptParams);
        return needEncryptParams.toString();
    }

    /**
     * 流转加密方法
     *
     * @param needEncryptParams 待加密串
     * @param encryptMethod     加密方法
     * @param key               约定秘钥
     * @return 加密后字符串
     */
    private static String encryptParams(String needEncryptParams, String encryptMethod, String key) {
        try {
            LOGGER.debug("加密前：{}", needEncryptParams);
            if (AES.equals(encryptMethod)) {
                return SHA256EncryptionUtils.encryptionSHA256(needEncryptParams);
            }
            if ("BASE64".equals(encryptMethod)) {
                return Base64.getUrlEncoder().encodeToString(needEncryptParams.getBytes());
            }
            throw new RuntimeException("不存在加密方法【" + encryptMethod + "】");
        } catch (Exception e) {
            LOGGER.error("用【{}】方法加密【{}】异常！", encryptMethod, needEncryptParams, e);
            return needEncryptParams;
        }
    }


    /**
     * 替换指定URL中的指定参数
     *
     * @param content  待替换的内容
     * @param datasMap 替换数据
     * @return 替换后的字符串
     */
    public static String replaceParams(String content, Map<String, Object> datasMap) {
        return replaceParams(content, datasMap, false);
    }

    /**
     * 替换指定URL中的指定参数
     *
     * @param content 待替换的内容
     * @param dataMap 替换数据
     * @param isAll   是否替换所有待替换的内容。
     *                为true时， 若某变量在dataMap中有可替换内容，则将其替换；若没有可替换内容，则替换为默认值
     *                为false时，若某变量在dataMap中有可替换内容，则将其替换；若没有可替换内容，则不做任何处理
     * @return 替换后的字符串
     */
    public static String replaceParams(String content, Map<String, Object> dataMap, boolean isAll) {
        String notFindValue = "";
        int leftIndex = 0;
        int rightIndex = 0;
        int length = 0;
        boolean isFoundEndTag = false;
        StringBuilder rtnContent;
        StringBuilder paramName;
        leftIndex = content.indexOf(REPLACE_LEFT_TAG);
        if (leftIndex < 0) {
            return content;
        }
        length = content.length();
        rtnContent = new StringBuilder();
        paramName = new StringBuilder();
        for (int i = (leftIndex + REPLACE_LEFT_TAG.length()); i < length; i++) {
            char c = content.charAt(i);
            if (c == REPLACE_RIGHT_TAG) {
                rightIndex = i;
                isFoundEndTag = true;
                break;
            } else {
                paramName.append(c);
            }
        }
        if (!isFoundEndTag) { //未找到闭合标签，抛出异常
            throw new RuntimeException("[" + content + "]替换错误," + REPLACE_LEFT_TAG + paramName.toString() + "未找到结束标记");
        }
        String value = getValue(paramName.toString(), dataMap, notFindValue);
        //如果替换字符串型如 "${product}" ,那么在替换变量的同时，删除左右两侧的双引号
        //此处这么种做法的目的是应为 在写js时候 var = ${}； 有语法错误，整个代码块不执行。给调试带来不便
        if (leftIndex > 0 && rightIndex < length - 1) {
            if (content.charAt(leftIndex - 1) == '"' && content.charAt(rightIndex + 1) == '"') {
                leftIndex = leftIndex - 1;
                rightIndex = rightIndex + 1;
            }
        }

        if (notFindValue.equals(value) && !isAll) {
            rtnContent.append(content.substring(0, rightIndex + 1));
        } else {
            rtnContent.append(content.substring(0, leftIndex));
            rtnContent.append(value);
        }
        rtnContent.append(replaceParams(content.substring(rightIndex + 1, length), dataMap, isAll));

        return rtnContent.toString();
    }

    private static String getValue(String key, Map<String, Object> dataMap, String notFindValue) {
        Queue<String> keysQueue = convertKeyToKeysQueue(key);
        Object rtnObj = getVariable(keysQueue, dataMap, notFindValue);
        if (rtnObj == null) {
            return notFindValue;
        } else {
            return rtnObj.toString();
        }
    }

    private static Queue<String> convertKeyToKeysQueue(String key) {
        Queue<String> queue = new LinkedList<>();
        String[] aryKeys = key.split(SEPARATOR);
        for (String oneKey : aryKeys) {
            queue.add(oneKey);
        }
        return queue;
    }


    private static Object getVariable(String key, Map<String, Object> dataMap) {
        String myKey = key;

        if (dataMap.containsKey(myKey)) {
            return dataMap.get(myKey);
        }
        myKey = toLowerCaseInitial(key);
        if (dataMap.containsKey(myKey)) {
            return dataMap.get(myKey);
        }
        return null;
    }

    /**
     * 首字母小写
     *
     * @param src
     * @return
     */
    private static String toLowerCaseInitial(String src) {
        if (isNullOrEmpty(src)) {
            return src;
        }
        char[] chars = src.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return String.valueOf(chars);
    }


    private static boolean isNullOrEmpty(String src) {
        if (src == null) {
            return true;
        }
        return src.trim().length() == 0;
    }

    /**
     * 递归方法，从obj中解析出来key对应的值
     *
     * @param keysQueue key有多个层级
     * @param obj
     * @return@Transactional
     */
    @SuppressWarnings("unchecked")
    private static Object getVariable(Queue<String> keysQueue, Object obj, String notFindValue) {
        String key;
        Object value;
        //校验传入参数正确性
        if (keysQueue.isEmpty()) {
            throw new RuntimeException("获取keysQueue时，发生意外错误,keysQueue.size()应大于0：keysQueue.size =" + keysQueue.size());
        }
        if (obj == null) {
            return notFindValue;
        }
        //用Key在Object中获取值
        key = keysQueue.poll();
        value = getVariable(key, (Map<String, Object>) obj);
        //跳出递归，返回值
        if (value == null) {
            return notFindValue;
        }
        if (keysQueue.isEmpty()) {
            return value;
        }
        //递归调用
        return getVariable(keysQueue, value, notFindValue);
    }

    /**
     * 获取指定url中的某个参数
     *
     * @param url
     * @return
     */
    public static UrlEntity getParamByUrl(String url) {
        UrlEntity entity = new UrlEntity();
        if (url == null) {
            return entity;
        }
        url = url.trim();
        if (url.equals("")) {
            return entity;
        }
        String[] urlParts = url.split("\\?");
        entity.setBaseUrl(urlParts[0]);
        //没有参数
        if (urlParts.length == 1) {
            return entity;
        }
        //有参数
        String[] params = urlParts[1].split("&");
        entity.setParams(new HashMap<>());
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length > 1) {
                if (keyValue[1] != null) {
                    entity.getParams().put(keyValue[0], keyValue[1]);
                } else {
                    entity.getParams().put(keyValue[0], "");
                }
            } else {
                entity.getParams().put(keyValue[0], "");
            }
        }
        return entity;
    }


}
