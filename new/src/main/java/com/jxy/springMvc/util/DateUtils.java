package com.jxy.springMvc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    static Calendar c = Calendar.getInstance();

    /**
     * 旧版本开始
     */

    /**
     * 以yyyy-MM-dd格式格式化
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 以自己的格式格式化
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 旧版本结束
     */

    /**
     * 以yyyy-MM-dd HH:mm:ss格式格式化
     */
    public static String formatT(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }

    /**
     * 将String转换成date
     */
    public static Date toDate(String stringDate) {
        try {
            return new SimpleDateFormat(DATE_TIME_PATTERN).parse(stringDate);
        } catch (ParseException e) {
            System.out.println("字符串转换时间出错！");
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * 按月倒推时间
     * 参数date:
     * 参数：backNumber：倒推月个数，4->倒推4个月
     */
    public static String getBackMonthTime(Date date, int backNumber) {
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - backNumber);
        return formatT(c.getTime());
    }
}
