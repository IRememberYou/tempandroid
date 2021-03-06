package com.example.pinan.tempandroid.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.pinan.tempandroid.R;

/**
 * @author pinan
 * @date 2017/12/28
 * 简单的弹框
 */

@SuppressLint("ValidFragment")
public class SimpleDialogFragment extends DialogFragment {
    private View view;
    
    public SimpleDialogFragment(Builder builder) {
        this.view = builder.itemView;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomDatePickerDialog);
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置无标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setAttributes(params);
    }
    
    public SimpleDialogFragment show(String... tags) {
        String tag = tags != null && tags.length > 0 ? tags[0] : "tag";
        super.show(((FragmentActivity) view.getContext()).getSupportFragmentManager(), tag);
        return this;
    }
    
    @Override
    public void onStart() {
        super.onStart();
        //设置fragment高度 、宽度
        int dialogHeight = (int) (getContext().getResources().getDisplayMetrics().heightPixels * 0.6);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, dialogHeight);
        getDialog().setCanceledOnTouchOutside(true);
    }
    
    public static class Builder {
        private Context context;
        private View itemView;
        
        public Builder(Context context) {
            this.context = context;
        }
        
        public Builder setResId(int resId) {
            itemView = LayoutInflater.from(context).inflate(resId, null);
            return this;
        }
        
        public Builder setText(int id, String text) {
            ((TextView) itemView.findViewById(id)).setText(text);
            return this;
        }
        
        public Builder setOnClickListener(int id, View.OnClickListener l) {
            itemView.findViewById(id).setOnClickListener(l);
            return this;
        }
        
        /**
         * 给 recycleview 设置点击事件
         *
         * @param id       recycleview 的 id
         * @param listener 自定义的点击事件{com.example.pinan.tempandroid.utils.RecyclerViewItemClick}
         * @return
         */
        public Builder addOnItemTouchListener(int id, RecyclerView.OnItemTouchListener listener) {
            RecyclerView recyclerView = itemView.findViewById(id);
            recyclerView.addOnItemTouchListener(listener);
            return this;
        }
        
        public <T extends View> T getView(int id) {
            return itemView.findViewById(id);
        }
        
        public CharSequence getText(int id) {
            TextView view = itemView.findViewById(id);
            return view.getText();
        }
        
        public Builder setRecycle(int id, RecyclerView.Adapter adapter, RecyclerView.LayoutManager... layoutManagers) {
            RecyclerView recyclerView = itemView.findViewById(id);
            //如果不设置,则默认是 LinearLayoutManager
            boolean b = layoutManagers != null && layoutManagers.length > 0;
            RecyclerView.LayoutManager layoutManager = b ? layoutManagers[0] : new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            return this;
        }
        
        public RecyclerView getRecycleView(int id) {
            return itemView.findViewById(id);
        }
        
        public View getItemView() {
            return itemView;
        }
        
        public SimpleDialogFragment build() {
            return new SimpleDialogFragment(this);
        }
    }
}
