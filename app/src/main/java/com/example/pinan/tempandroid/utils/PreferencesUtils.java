package com.example.pinan.tempandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author pinan
 * @date 2018/1/4
 */

public class PreferencesUtils {
    
    private static PreferencesUtils instance = null;
    private final SharedPreferences mPreferences;
    
    private PreferencesUtils(Context context) {
        mPreferences = context.getSharedPreferences("tempandroid", Context.MODE_PRIVATE);
    }
    
    public static PreferencesUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (PreferencesUtils.class) {
                if (instance == null) {
                    instance = new PreferencesUtils(context);
                }
            }
        }
        return instance;
    }
    /**
     * -----------------------int-------------------------------
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }
    
    public int getInt(String key, int defValues) {
        return mPreferences.getInt(key, defValues);
    }
    
    public void putInt(String key, int values) {
        mPreferences.edit().putInt(key, values).apply();
    }
    
    /**
     * -----------------------string-------------------------------
     */
    public String getString(String key) {
        return getString(key, "#null");
    }
    
    public String getString(String key, String defValues) {
        return mPreferences.getString(key, defValues);
    }
    
    public void putString(String key, String defvalues) {
        mPreferences.edit().putString(key, defvalues).apply();
    }
}
