package com.example.pinan.tempandroid.home.adapter;

import android.view.View;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.QuickAdapter;
import com.example.pinan.tempandroid.home.bean.SmsMessage;

import java.util.List;

/**
 * @author pinan
 * @date 2017/12/28
 */

public class SmsListAdapter extends QuickAdapter<SmsMessage> {
    public SmsListAdapter(List<SmsMessage> list) {
        super(R.layout.item_sms_list, list);
    }
    
    @Override
    protected void onBindViewHolder(ViewHolder holder, SmsMessage smsMessage) {
        holder.setText(R.id.tv_address, smsMessage.phone)
            .setText(R.id.tv_body, smsMessage.content);
        //是否显示小红点,0未读,1已读
        int visibility = "0".equals(smsMessage.read) ? View.VISIBLE : View.INVISIBLE;
        holder.getView(R.id.red_point).setVisibility(visibility);
    }
}
