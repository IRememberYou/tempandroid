package com.example.pinan.tempandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.pinan.tempandroid.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pinan
 * @date 2017/12/28
 */

public class PhotoUtil {
    public static final int REQUEST_CODE_PICK_IMAGE = 1001;
    
    /**
     * 获取图册单个图片
     */
    public static void selectSinglePic(BaseActivity context, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        context.startActivityForResult(intent, (requestCode != 0 ? requestCode : REQUEST_CODE_PICK_IMAGE));
    }
    
    /**
     * 获取图册多个图片
     */
    public void selectMorePic() {
    
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
            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            //获取图片的详细信息
            String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
            List<Object> temp = new ArrayList<>();
            temp.add(name);
            temp.add(data);
            temp.add(desc);
            list.add(temp);
        }
        cursor.close();
        return list;
    }
}
