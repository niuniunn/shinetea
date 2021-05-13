package com.niuniu.shinetea.utils;

public class DateBetweenUtil {

    /**
     *
     * @param date1  开始时间
     * @param date2  结束时间
     * @param date  要判断的时间
     * @return  date在date1 和date2之间 返回true 否则返回false
     */
    public static Boolean isDateBetweenAAndB(String date1, String date2, String date) {
        return date1.compareTo(date) <= 0 && date2.compareTo(date) >= 0;
    }
}
