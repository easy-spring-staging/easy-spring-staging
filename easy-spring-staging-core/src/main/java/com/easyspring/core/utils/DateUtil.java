/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类  .
 *
 * <p>
 * 日期时间工具类
 *
 * @author caobaoyu
 * @date 2020/11/2 10:36
 */
public class DateUtil {

    public static Date getNextDate(Date date){
        Date nextDate = null;
        if(date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 1);
            nextDate = cal.getTime();
        }
        return nextDate;
    }
    public static Date getCurrentDate(){
        return new Date(System.currentTimeMillis());
    }
}
