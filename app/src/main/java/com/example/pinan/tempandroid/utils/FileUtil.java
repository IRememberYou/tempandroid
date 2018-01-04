package com.example.pinan.tempandroid.utils;

/**
 * @author pinan
 * @date 2018/1/2
 */

import android.os.Environment;

/**
 * 文件管理
 */
public class FileUtil {
    
    /**
     * 判断sdcard是否被挂载
     */
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
