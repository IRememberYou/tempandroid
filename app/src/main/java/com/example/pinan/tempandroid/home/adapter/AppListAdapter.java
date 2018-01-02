package com.example.pinan.tempandroid.home.adapter;

import android.widget.ImageView;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.QuickAdapter;
import com.example.pinan.tempandroid.home.bean.AppBean;

import java.util.List;

/**
 * @author pinan
 * @date 2018/1/2
 */

public class AppListAdapter extends QuickAdapter<AppBean> {
    public AppListAdapter(List<AppBean> list) {
        super(R.layout.item_app_list, list);
    }
    
    @Override
    protected void onBindViewHolder(ViewHolder holder, AppBean bean) {
        String str = bean.isSystemApp ? "系统应用" : "第三方 app";
        str = "  (" + str + ")";
        holder.setText(R.id.tv_app_name, bean.name + str)
            .setText(R.id.tv_first, bean.firstInstallTime)
            .setText(R.id.tv_last, bean.lastUpdateTime)
            .setText(R.id.tv_verCode, bean.versionCode + "")
            .setText(R.id.tv_verName, bean.versionName);
        ((ImageView) holder.getView(R.id.icon_app)).setImageDrawable(bean.icon);
    }
}
