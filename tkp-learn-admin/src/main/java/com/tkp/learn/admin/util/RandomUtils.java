package com.tkp.learn.admin.util;

import com.tkp.learn.admin.actuator.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.UUID;


/**
 * 随机数或字符的工具类
 */
public abstract class RandomUtils {

    private RandomUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static SecureRandom random;

    private static final Object[] CODE_NUMBER = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomUtils.class);

    static {

        try {
            random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            LOGGER.error("", e);
            throw new ServiceException(e);
        }
    }

    /**
     * 获取指定位数的随机数
     *
     * @param decimals
     * @return 指定位数的随机数
     */
    public static final String getRandomNum(int decimals) {
        return getRandomNum(decimals, CODE_NUMBER);
    }


    /**
     * 获取随机数
     *
     * @param decimals 获取的个数
     * @param source   获取来源
     * @return 随机数
     */
    public static final String getRandomNum(int decimals, Object[] source) {
        StringBuilder builder = new StringBuilder();
        while (decimals-- > 0) {
            builder.append(source[random.nextInt(source.length)]);
        }
        return builder.toString();
    }

    /**
     * 获取随机的UUID实例
     *
     * @return UUID
     */
    public static UUID randomUUID() {
        return UUID.randomUUID();
    }

    /**
     * 获取随机的UUID字符串
     *
     * @return UUID字符串
     */
    public static String randomGUID() {
        return randomUUID().toString();
    }

    /**
     * 获取随机的大写的UUID字符串
     *
     * @return 大写的UUID字符串
     */
    public static String randomUpperCaseGUID() {
        return randomGUID().toUpperCase();
    }

}
