package com.example.pinan.tempandroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.BaseActivity;
import com.example.pinan.tempandroid.base.SimpleDialogFragment;
import com.example.pinan.tempandroid.home.adapter.PhoneViewPagerAdapter;
import com.example.pinan.tempandroid.utils.PhoneUtil;

import butterknife.BindView;
import butterknife.OnClick;

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
    
    @OnClick(R.id.fab)
    void onFabClick() {
        final SimpleDialogFragment.Builder builder = new SimpleDialogFragment.Builder(this)
            .setResId(R.layout.dialog_call_phone);
        builder.setOnClickListener(R.id.btn_call, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = builder.getText(R.id.et_number).toString();
                PhoneUtil.call(PhoneActivity.this, number);
            }
        });
        builder.build().show(getSupportFragmentManager(), "tag");
    }
}