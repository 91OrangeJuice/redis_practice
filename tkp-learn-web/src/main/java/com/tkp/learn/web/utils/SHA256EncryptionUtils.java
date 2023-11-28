package com.tkp.learn.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class SHA256EncryptionUtils {

    private SHA256EncryptionUtils() {
        throw new IllegalStateException("SHA256EncryptionUtils class");
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(SHA256EncryptionUtils.class);

    public static byte[] SHA256(String str) {
        byte[] mdArray = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes("UTF-8"));
            mdArray = md.digest();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("MessageDigest getInstance error{}", e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("String getBytes error{}", e);
        }
        return mdArray;
    }

    public static String encryptionSHA256(String str) {
        String encryptionStr = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes("UTF-8"));
            byte[] mdArray = md.digest();
            encryptionStr = byte2Hex(mdArray);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("MessageDigest getInstance error{}", e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("String getBytes error{}", e);
        }
        return encryptionStr;
    }

    private static String byte2Hex(byte[] bytes) {
        final String HEX = "0123456789abcdef";
        StringBuilder stringBuilder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            stringBuilder.append(HEX.charAt((b >> 4) & 0x0f));
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            stringBuilder.append(HEX.charAt(b & 0x0f));
        }
        return stringBuilder.toString();
    }

    public static String encryptionSHA256Base64UrlSafe(String str) {
        return Base64.getUrlEncoder().encodeToString(SHA256(str));
    }

}
