package com.example.pinan.tempandroid.utils;

import android.content.Intent;

import com.example.pinan.tempandroid.base.BaseActivity;

/**
 * @author pinan
 * @date 2017/12/28
 */

public class PhotoUtil {
    private static int REQUEST_CODE_PICK_IMAGE = 1001;
    
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
    public void getAllPic() {
    
    }
}
