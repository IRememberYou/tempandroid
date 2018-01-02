package com.example.pinan.tempandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.pinan.tempandroid.home.bean.AppBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pinan
 * @date 2018/1/2
 */

public class AppUtil {
    /**
     * 获取已安装的 app
     */
    public static List<AppBean> getAllApp(Context context) {
        //存储 app
        List<AppBean> appLists = new ArrayList<>();
        //获取包管理器
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            
            String name = packageInfo.applicationInfo.loadLabel(pm).toString();
            String packageName = packageInfo.applicationInfo.packageName;
            Drawable icon = packageInfo.applicationInfo.loadIcon(pm);
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            //返回0:非系统应用,否则就是系统应用
            int isSystemApp = packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM;
            //第一次安装的时间
            long firstInstallTime = packageInfo.firstInstallTime;
            long lastUpdateTime = packageInfo.lastUpdateTime;
            AppBean bean = new AppBean();
            bean.icon = icon;
            bean.name = name;
            bean.versionCode = versionCode;
            bean.versionName = versionName;
            bean.isSystemApp = (isSystemApp != 0);
            bean.lastUpdateTime = DateUtil.dateFormat(lastUpdateTime + "");
            bean.firstInstallTime = DateUtil.dateFormat(firstInstallTime + "");
            bean.packageName = packageName;
            appLists.add(bean);
        }
        return appLists;
    }
    
    
    /**
     * 检测是否安装了某个 app
     */
    public boolean isAppInstalled(Context context, String pkgName) {
//        //方式一:
//        boolean installed = false;
//        if (pkgName == null) {
//            return installed;
//        }
//        PackageManager pm = context.getPackageManager();
//        try {
//            pm.getPackageInfo(pkgName, PackageManager.GET_ACTIVITIES);
//            installed = true;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//            installed = false;
//        }
//        return installed;
        
        //方式二:
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(pkgName);
    }
    
    /**
     * 通过包名启动一个 app
     */
    public static void startApp(Context context, String pkgName) {
//        方式一:
//        //创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
//        Intent intent = new Intent(Intent.ACTION_MAIN, null);
//        intent.setPackage(pkgName);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//
//        // 通过getPackageManager()的queryIntentActivities方法遍历获取启动类的名字
//        String className = null;
//        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities((Intent) intent.clone(), 0);
//        ResolveInfo info = resolveInfos.iterator().next();
//        if (info != null) {
//            className = info.activityInfo.name;
//        }
//        ComponentName component = new ComponentName(pkgName, className);
//        intent.setComponent(component);
//        context.startApp(intent);

//        方式二:(线上 app 使用这种的居多)
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(pkgName);
        context.startActivity(intent);
    }
}
