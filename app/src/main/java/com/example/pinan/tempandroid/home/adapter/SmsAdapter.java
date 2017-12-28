package com.example.pinan.tempandroid.home.adapter;

import android.view.View;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.QuickAdapter;
import com.example.pinan.tempandroid.home.bean.SmsMessage;

import java.util.List;

/**
 * @author pinan
 * @date 2017/12/27
 */

public class SmsAdapter extends QuickAdapter<SmsMessage> {
    public SmsAdapter(List<SmsMessage> list) {
        super(R.layout.item_sms, list);
    }
    
    @Override
    protected void onBindViewHolder(QuickAdapter<SmsMessage>.ViewHolder holder, SmsMessage smsMessage) {
        if ("1".equals(smsMessage.type)) {
            //接收的短信
            holder.getView(R.id.rl_receive).setVisibility(View.VISIBLE);
            holder.getView(R.id.rl_send).setVisibility(View.GONE);
    
            holder.setText(R.id.tv_receive_address, smsMessage.phone)
                .setText(R.id.tv_receive_body, smsMessage.content)
                .setText(R.id.tv_receive_time, smsMessage.time);
            
        } else if ("2".equals(smsMessage.type)) {
            //发送的短信
            holder.getView(R.id.rl_send).setVisibility(View.VISIBLE);
            holder.getView(R.id.rl_receive).setVisibility(View.GONE);
    
            holder.setText(R.id.tv_send_address, smsMessage.phone)
                .setText(R.id.tv_send_body, smsMessage.content)
                .setText(R.id.tv_send_time, smsMessage.time);
        }
    }
}
