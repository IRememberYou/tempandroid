package com.example.pinan.tempandroid.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.pinan.tempandroid.utils.DpUtil;

/**
 * @author pinan
 * @date 2017/12/27
 */

public class MeasureTextView extends android.support.v7.widget.AppCompatTextView {
    
    public MeasureTextView(Context context) {
        super(context);
    }
    
    public MeasureTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    
    public MeasureTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(widthMeasureSpec));
    }
    
    private int measure(int width) {
        int result = 0;
        int widthMode = MeasureSpec.getMode(width);
        int widthSize = MeasureSpec.getSize(width);
        if (widthMode == MeasureSpec.EXACTLY) {
            result = widthSize;
        } else {
            result = DpUtil.dp2px(getContext(), 100);
        }
        return result;
    }
}
