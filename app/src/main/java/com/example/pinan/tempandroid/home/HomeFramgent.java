package com.example.pinan.tempandroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.BaseFragemnt;
import com.example.pinan.tempandroid.base.SimpleDialogFragment;
import com.example.pinan.tempandroid.home.adapter.AppListAdapter;
import com.example.pinan.tempandroid.home.adapter.HomeAdapter;
import com.example.pinan.tempandroid.home.bean.AppBean;
import com.example.pinan.tempandroid.utils.AppUtil;
import com.example.pinan.tempandroid.utils.RecyclerViewItemClick;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        "已装的 app",
        "图片"
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
                
                switch (position) {
                    case 0:
                        //电话
                        startActivity(PhoneActivity.class);
                        break;
                    case 1:
                        //短信
                        startActivity(ResultDealActivity.class);
                        break;
                    case 2:
                        //文件清理
                        break;
                    case 3:
                        //已装的 app
                        List<AppBean> allApp = AppUtil.getAllApp(mContext);
                        Collections.sort(allApp, new Comparator<AppBean>() {
                            @Override
                            public int compare(AppBean o1, AppBean o2) {
                                return o2.lastUpdateTime.compareTo(o1.lastUpdateTime);
                            }
                        });
                        SimpleDialogFragment.Builder builder = new SimpleDialogFragment.Builder(mContext);
                        builder.setResId(R.layout.common_recycle_view);
                        final RecyclerView recyclerView = builder.getView(R.id.recycle_view);
                        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                        recyclerView.setAdapter(new AppListAdapter(allApp));
                        recyclerView.addOnItemTouchListener(new RecyclerViewItemClick() {
                            @Override
                            protected void onItemClick(View view, int position) {
                                AppBean item = ((AppListAdapter) recyclerView.getAdapter()).getItem(position);
                                AppUtil.startApp(mContext, item.packageName);
                            }
                        });
                        builder.build().show(getChildFragmentManager(), "child");
                        break;
                    case 4:
                        //图片
                        startActivity(PictureActivity.class);
                        break;
                    default:
                        break;
                    
                }
            }
        });
    }
}
