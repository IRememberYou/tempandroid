package com.example.pinan.tempandroid.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author pinan
 * @date 2017/12/27
 */

public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.ViewHolder> {
    private List<T> list;
    private int resId;
    
    public QuickAdapter(int resId, List<T> list) {
        this.list = list;
        this.resId = resId;
    }
    
    public T getItem(int position) {
        return list.get(position);
    }
    
    @Override
    public QuickAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(QuickAdapter.ViewHolder holder, int position) {
        onBindViewHolder(holder, list.get(position));
    }
    
    protected abstract void onBindViewHolder(ViewHolder holder, T t);
    
    @Override
    public int getItemCount() {
        return list.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
        
        public ViewHolder setText(int id, String text) {
            ((TextView) itemView.findViewById(id)).setText(text);
            return this;
        }
        
        public <T extends View> T getView(int id) {
            return itemView.findViewById(id);
        }
    }
}
