package com.example.pinan.tempandroid.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author pinan
 * @date 2017/12/27
 */

public class DateUtil {
    
    /**
     * 格式化时间 yyyy-MM-dd HH-mm-ss
     */
    public static String dateFormat(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return sdf.format(new Date(Integer.parseInt(time)));
    }
}
