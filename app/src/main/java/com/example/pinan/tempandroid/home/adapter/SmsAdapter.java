package com.example.pinan.tempandroid.home.adapter;

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
    protected void dealBindViewHolder(QuickAdapter<SmsMessage>.ViewHolder holder, SmsMessage smsMessage) {
        if ("1".equals(smsMessage.type)) {
        
        } else if ("2".equals(smsMessage.type)) {
        
        }
        
    }
}
