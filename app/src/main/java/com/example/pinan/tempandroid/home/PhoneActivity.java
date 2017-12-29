package com.example.pinan.tempandroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.BaseActivity;
import com.example.pinan.tempandroid.home.adapter.PhoneViewPagerAdapter;

import butterknife.BindView;

/**
 * @author pinan
 * @date 2017/12/28
 */

public class PhoneActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        mViewPager.setAdapter(new PhoneViewPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }
}