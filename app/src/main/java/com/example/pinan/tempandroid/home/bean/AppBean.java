package com.example.pinan.tempandroid.home.bean;

import android.graphics.drawable.Drawable;

/**
 * @author pinan
 * @date 2018/1/2
 */

public class AppBean {
    public String name;//app 名字
    public Drawable icon;//app 图标
    public int versionCode;//版本号
    public String versionName;//版本名
    public boolean isSystemApp;//是否系统自带应用
    
    public String lastUpdateTime;//最后一次更新时间
    public String firstInstallTime;//第一次安装的时间
    public String packageName;// 包名
    
    public String cache;//储存大小
    public String preTime;//上次打开时间
    public String flow;//使用了多少流量
    public String power;//耗电量
    public boolean isRun;//是否在运行
}
