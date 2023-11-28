package com.tkp.learn.web.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/4
 * @version: 1.0
 */
public class DateUtils {

    public static String getDateBeginOrEnd(Date date, Boolean flag) {
        String time = null;
        SimpleDateFormat dateformat1 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Calendar calendar1 = Calendar.getInstance();
        if (flag == true) {
            calendar1.setTime(date);
            calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                    0, 0, 0);
            Date beginOfDate = calendar1.getTime();
            time = dateformat1.format(beginOfDate);
        } else {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date);
            calendar1.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH),
                    23, 59, 59);
            Date endOfDate = calendar1.getTime();
            time = dateformat1.format(endOfDate);
        }
        return time;
    }

    public static String getYesterdayBegin() {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = c.getTime();
        return getDateBeginOrEnd(yesterday, true);
    }

    public static String getYesterdayEnd() {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = c.getTime();
        return getDateBeginOrEnd(yesterday, false);
    }

    public static int getYesterdayYear() {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, -1);
        int year = c.get(Calendar.YEAR);
        return year;
    }

    public static int getYesterdayMonth() {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, -1);
        int month = c.get(Calendar.MONTH);
        return month;
    }

}
