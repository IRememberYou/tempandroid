package com.example.pinan.tempandroid.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.widget.MeasureTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author pinan
 * @date 2017/12/27
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    
    private final String[] mModules;
    
    public HomeAdapter(String[] modules) {
        mModules = modules;
    }
    
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        holder.tvModel.setText(mModules[position]);
    }
    
    @Override
    public int getItemCount() {
        return mModules.length;
    }
    
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_model)
        MeasureTextView tvModel;
        
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
