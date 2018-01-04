package com.example.pinan.tempandroid.home.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.QuickAdapter;

import java.util.List;

/**
 * @author pinan
 * @date 2018/1/3
 */

public class PhotoAllAdapter extends QuickAdapter<List<Object>> {
    public PhotoAllAdapter(List<List<Object>> list) {
        super(R.layout.item_photo_all, list);
    }
    
    @Override
    protected void onBindViewHolder(ViewHolder holder, List<Object> objects) {
        String name = (String) objects.get(0);
        String data = (String) objects.get(1);
        String desc = (String) objects.get(2);
        
        holder.setText(R.id.tv_name, name)
            .setText(R.id.tv_location, data)
            .setText(R.id.tv_desc, desc);
        
        Bitmap bm = BitmapFactory.decodeFile(data);
        ((ImageView) holder.getView(R.id.iv_image)).setImageBitmap(bm);
        
    }
}
