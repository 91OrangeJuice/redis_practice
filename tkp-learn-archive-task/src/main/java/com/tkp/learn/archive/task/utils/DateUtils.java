package com.tkp.learn.archive.task.utils;

import java.text.DateFormat;
import java.text.ParseException;
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

    public static String getStringByDate(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return f.format(date);
    }

    public static Date getDateByString(String date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = f.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static String getLastMonthDate(Date today) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.MONTH, -1);
        Date lastMonth = c.getTime();
        SimpleDateFormat f = new SimpleDateFormat("YYYY-MM-dd");
        return f.format(lastMonth);
    }

    public static Date addDate(Date today, int amount, int unit) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(unit, amount);
        Date newDate = c.getTime();
        return newDate;
    }

    public static String getTodayBegin() {
        Date today = new Date();
        return getDateBeginOrEnd(today, true);
    }

    public static String getTodayEnd() {
        Date today = new Date();
        return getDateBeginOrEnd(today, false);
    }

    public static Date getNowDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDateStr=df.format(new Date());
        Date newDate=null;
        try {
            newDate= df.parse(nowDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

}
