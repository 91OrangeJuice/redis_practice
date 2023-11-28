package com.tkp.learn.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by itw_wangshuai01 on 2020/4/22.
 */
public class SHA1Util {

    private SHA1Util() {
        throw new IllegalStateException("SHA1Util class");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SHA1Util.class);

    public static String sha1(String src) {
        MessageDigest crypt = null;
        byte[] bytes = null;
        try {
            crypt = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("MessageDigest getInstance error{}！",e);
        }
        if (crypt != null) {
            crypt.reset();
            try {
                crypt.update(src.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("String getBytes error{}！",e);
            }
            bytes = crypt.digest();
        }
        return bytes == null ? null : byteToHex(bytes);
    }

    private static String byteToHex(byte[] hash) {
        StringBuilder sb = new StringBuilder(hash.length * 2);
        int tmp;
        for (byte b : hash) {
            tmp = b & 0xff;
            if (tmp < 16)
                sb.append("0");
            sb.append(Integer.toHexString(tmp));
        }
        return sb.toString();
    }
}
