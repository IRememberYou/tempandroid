package com.example.pinan.tempandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author pinan
 * @date 2017/12/27
 */

public class BaseActivity extends AppCompatActivity {
    
    private Unbinder mBind;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
    
    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mBind = ButterKnife.bind(this);
    }
    
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mBind = ButterKnife.bind(this);
    }
    
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        mBind = ButterKnife.bind(this);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
