package com.tkp.learn.web.utils;

import java.util.Base64;

public final class Base64Util {

    private Base64Util() {
        throw new IllegalStateException("Base64Util class");
    }


    /**
     * 编码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] base64Encrypt(byte[] data) throws Exception {
        return Base64.getEncoder().encode(data);
    }

    /**
     * 解码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] base64Decrypt(byte[] data) throws Exception {
        return Base64.getDecoder().decode(data);

    }

    /**
     * URL安全编码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] base64EncryptUrlSafe(byte[] data) throws Exception {
        return Base64.getUrlEncoder().encode(data);
    }

    /**
     * URL安全解码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] base64DecryptUrlSafe(byte[] data) throws Exception {
        return Base64.getUrlDecoder().decode(data);
    }

    /**
     * 编码
     *
     * @param data
     * @return
     */
    public static String encrypt(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    /***
     * 	解码
     *
     * @param data
     * @return
     */
    public static String decrypt(String data) {
        byte[] datas = Base64.getDecoder().decode(data.getBytes());
        return new String(datas);
    }

    /**
     * URL安全编码
     *
     * @param data
     * @return
     */
    public static String encryptUrlSafe(String data) {
        byte[] datas = Base64.getUrlEncoder().encode(data.getBytes());
        return new String(datas);
    }

    /**
     * URL安全解码
     *
     * @param data
     * @return
     */
    public static String decryptUrlSafe(String data) {
        byte[] datas = Base64.getUrlDecoder().decode(data.getBytes());
        return new String(datas);
    }
}
