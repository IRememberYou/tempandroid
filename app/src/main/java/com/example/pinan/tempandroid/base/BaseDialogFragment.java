package com.example.pinan.tempandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author pinan
 * @date 2017/12/28
 */

public abstract class BaseDialogFragment extends DialogFragment {
    
    protected Context mContext;
    private Unbinder mBind;
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mBind = ButterKnife.bind(this, view);
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
        mBind.unbind();
    }
    
    protected abstract int getLayoutId();
}
