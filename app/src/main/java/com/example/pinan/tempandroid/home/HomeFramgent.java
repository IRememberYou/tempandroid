package com.example.pinan.tempandroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.ResultDealActivity;
import com.example.pinan.tempandroid.base.BaseFragemnt;
import com.example.pinan.tempandroid.home.adapter.HomeAdapter;
import com.example.pinan.tempandroid.utils.RecyclerViewItemClick;

import butterknife.BindView;

/**
 * @author pinan
 * @date 2017/12/27
 */

public class HomeFramgent extends BaseFragemnt {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private String[] modules = {
        "电话",
        "短信",
        "文件清理",
        "已装的 app"
    };
    
    @Override
    protected int getLayoutResId() {
        return R.layout.home_fragment;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        HomeAdapter homeAdapter = new HomeAdapter(modules);
        mRecyclerView.setAdapter(homeAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerViewItemClick() {
            @Override
            protected void onItemClick(View view, int position) {
                Toast.makeText(mContext, modules[position], Toast.LENGTH_SHORT).show();
                if (position == 1) {
                    startActivity(ResultDealActivity.class);
                }
            }
        });
    }
    
   
}
