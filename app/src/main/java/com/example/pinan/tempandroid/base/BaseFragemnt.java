package com.example.pinan.tempandroid.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author pinan
 * @date 2017/12/27
 */

public abstract class BaseFragemnt extends Fragment {
    
    protected Context mContext;
    private Unbinder mUnbinder;
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        if (mContext != null) {
            mContext = null;
        }
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
    
    protected abstract int getLayoutResId();
    
    protected void startActivity(Class clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
    }
    
    protected void startActivity(Class clazz, Map<String, Serializable> extra) {
        Intent intent = new Intent(mContext, clazz);
        Set<String> keys = extra.keySet();
        for (String key : keys) {
            intent.putExtra(key, extra.get(key));
        }
        startActivity(intent);
    }
}
