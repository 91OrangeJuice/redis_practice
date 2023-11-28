package com.tkp.learn.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * 
 * @author zhangqiu
 * @version $Id: MD5.java, v 1.0 2016年10月25日 下午8:12:53 Exp $
 */
public class MD5 {
    private final static Logger LOGGER = LoggerFactory.getLogger(MD5.class);
    // 全局数组
    private static final String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f"   };

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bByte.length; i++) {
            stringBuilder.append(byteToArrayString(bByte[i]));
        }
        return stringBuilder.toString();
    }

    public static String getMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = strObj;
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (Exception ex) {
            LOGGER.error("数据加密出现异常，原因为{}",ex);
        }
        return resultString;
    }
}
