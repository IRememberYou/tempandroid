package com.example.pinan.tempandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.ExifInterface;
import android.provider.MediaStore;
import android.util.Log;

import com.yanzhenjie.album.Album;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pinan
 * @date 2017/12/28
 */

public class PhotoUtil {
    public static final int REQUEST_CODE_PICK_IMAGE = 1001;
    public static final int PICK_IMAGE = 1002;
    private static final String TAG = "PhotoUtil";
    
    /**
     * 获取图册单个图片
     */
    public static void selectSinglePic(Activity activity, int requestCode) {
//        //方式一:
//        Intent intent;
//        if (Build.VERSION.SDK_INT < 19) {
//            intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.setType("image/*");
//
//        } else {
//            intent = new Intent(
//                Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        }
//        activity.startActivityForResult(intent, (requestCode != 0 ? requestCode : REQUEST_CODE_PICK_IMAGE));
        //方式二:
        Intent intent2 = new Intent(Intent.ACTION_PICK);
//        Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
        intent2.setType("image/*");//相片类型
        
        
        activity.startActivityForResult(intent2, (requestCode != 0 ? requestCode : REQUEST_CODE_PICK_IMAGE));
    }
    
    /**
     * 获取图册多个图片
     */
    public static void selectMorePic(Activity activity, int requestCode) {
        //方式一:用第三方库
        Album.startAlbum(activity, 1002, 9);
        //方式二:
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        Intent.createChooser(intent, "Select Picture");
//        activity.startActivityForResult(intent, PICK_IMAGE);
    }
    
    /**
     * 获取手机图片所有图片,并展示
     */
    public static List<List<Object>> getAllPic(Context context) {
        List<List<Object>> list = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            //获取图片的名称
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            //获取图片的生成日期(图片地址)
            String data = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            //获取图片的详细信息
            String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
            List<Object> temp = new ArrayList<>();
            temp.add(name);
            temp.add(data);
            temp.add(desc);
            list.add(temp);
            
            try {
                ExifInterface exifInterface = new ExifInterface(data);
                String TAG_APERTURE = exifInterface.getAttribute(ExifInterface.TAG_APERTURE);
                String TAG_DATETIME = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
                String TAG_EXPOSURE_TIME = exifInterface.getAttribute(ExifInterface.TAG_EXPOSURE_TIME);
                String TAG_FLASH = exifInterface.getAttribute(ExifInterface.TAG_FLASH);
                String TAG_FOCAL_LENGTH = exifInterface.getAttribute(ExifInterface.TAG_FOCAL_LENGTH);
                String TAG_IMAGE_LENGTH = exifInterface.getAttribute(ExifInterface.TAG_IMAGE_LENGTH);
                String TAG_IMAGE_WIDTH = exifInterface.getAttribute(ExifInterface.TAG_IMAGE_WIDTH);
                String TAG_ISO = exifInterface.getAttribute(ExifInterface.TAG_ISO);
                String TAG_MAKE = exifInterface.getAttribute(ExifInterface.TAG_MAKE);
                String TAG_MODEL = exifInterface.getAttribute(ExifInterface.TAG_MODEL);
                String TAG_ORIENTATION = exifInterface.getAttribute(ExifInterface.TAG_ORIENTATION);
                String TAG_WHITE_BALANCE = exifInterface.getAttribute(ExifInterface.TAG_WHITE_BALANCE);
                
                Log.i(TAG, "光圈值:" + TAG_APERTURE);
                Log.i(TAG, "拍摄时间:" + TAG_DATETIME);
                Log.i(TAG, "曝光时间:" + TAG_EXPOSURE_TIME);
                Log.i(TAG, "闪光灯:" + TAG_FLASH);
                Log.i(TAG, "焦距:" + TAG_FOCAL_LENGTH);
                Log.i(TAG, "图片高度:" + TAG_IMAGE_LENGTH);
                Log.i(TAG, "图片宽度:" + TAG_IMAGE_WIDTH);
                Log.i(TAG, "ISO:" + TAG_ISO);
                Log.i(TAG, "设备品牌:" + TAG_MAKE);
                Log.i(TAG, "设备型号:" + TAG_MODEL);
                Log.i(TAG, "旋转角度:" + TAG_ORIENTATION);
                Log.i(TAG, "白平衡:" + TAG_WHITE_BALANCE);
                Log.d(TAG, "-----------------------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return list;
    }
}
