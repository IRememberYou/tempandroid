package com.example.pinan.tempandroid.utils;

import android.content.Context;

/**
 * @author pinan
 * @date 2017/12/27
 */

public class DpUtil {
    public static int dp2px(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5);
    }
}
