package com.example.pinan.tempandroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.BaseActivity;
import com.example.pinan.tempandroid.utils.PhotoUtil;

import butterknife.OnClick;

/**
 * @author pinan
 * @date 2018/1/2
 */

public class PictureActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
    }
    
    @OnClick(R.id.tv_single_pic)
    void onSinglePicClick() {
        PhotoUtil.selectSinglePic(this);
        
    }
}
