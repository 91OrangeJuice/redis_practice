package com.tkp.learn.web.utils;

import com.tkp.learn.web.constant.Constant;

import java.math.BigDecimal;


/**
 * author: itw_lixg01
 * Date: 2020/3/7 11:34
 * Description：数值处理工具类
 */
public class NumberUtil {

    private NumberUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 格式化小数，默认四舍五入，保留4位小数
     * @param d 被格式化的数值
     * @return 格式化后的数值
     */
    public static double formatDouble(double d) {
        return formatDouble(d, 4);
    }

    /**
     * 格式化小数，默认四舍五入
     * @param d 被格式化的数值
     * @param scale 保留小数位数
     * @return 格式化后的数值
     */
    public static double formatDouble(double d, int scale) {
        return formatDouble(d, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 格式化小数
     * @param d 被格式化的数值
     * @param scale 保留小数位数
     * @param mode 格式化方式
     * @return 格式化后的数值
     */
    public static double formatDouble(double d, int scale, int mode) {
        BigDecimal bigDecimal = new BigDecimal(d);
        return bigDecimal.setScale(scale, mode).doubleValue();
    }

    /**
     * 秒数转换为分钟数
     * @param seconds 秒数
     * @return 分钟数
     */
    public static int secondsToMinutes(int seconds) {
        return (int) Math.ceil((seconds * 1.00) / Constant.SECONDS_PER_MINUTE);
    }
   
}
