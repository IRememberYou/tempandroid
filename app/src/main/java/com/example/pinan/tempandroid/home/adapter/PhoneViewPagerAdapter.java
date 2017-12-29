package com.example.pinan.tempandroid.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pinan.tempandroid.home.PhoneListFragment;

/**
 * @author pinan
 * @date 2017/12/29
 */
public class PhoneViewPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = {"通话记录", "联系人"};
    
    public PhoneViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    
    @Override
    public Fragment getItem(int position) {
        return PhoneListFragment.newInstance(titles[position]);
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
    
    @Override
    public int getCount() {
        return titles.length;
    }
}
