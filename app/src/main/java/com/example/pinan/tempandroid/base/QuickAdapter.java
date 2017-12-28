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
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        dealBindViewHolder(holder, list.get(position));
    }
    
    protected abstract void dealBindViewHolder(ViewHolder holder, T t);
    
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
        
        public void setText(int id, String text) {
            ((TextView) itemView.findViewById(id)).setText(text);
        }
        
        public <T extends View> T getView(int id) {
            return itemView.findViewById(id);
        }
    }
}
