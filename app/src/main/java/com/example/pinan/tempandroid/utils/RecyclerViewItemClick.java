package com.example.pinan.tempandroid.utils;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author pinan
 * @date 2017/12/27
 */

/**
 * recycleview 的条目点击事件
 */
public abstract class RecyclerViewItemClick implements RecyclerView.OnItemTouchListener {
    private RecyclerView touchView;
    private GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapUp(MotionEvent ev) {
            final View childView = touchView.findChildViewUnder(ev.getX(), ev.getY());
            if (childView != null) {
                onItemClick(childView, touchView.getChildAdapterPosition(childView));
            }
            return true;
        }
    });
    
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        touchView = rv;
        return false;
    }
    
    protected abstract void onItemClick(View view, int position);
    
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    
    }
    
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    
    }
    
}
