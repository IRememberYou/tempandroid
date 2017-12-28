package com.example.pinan.tempandroid.home.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author pinan
 * @date 2017/12/27
 */

/**
 * 通过广播监听收到的短信
 */
public class SmsReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) object);
                //电话号码
                String phone = sms.getOriginatingAddress();
                //内容
                String emailBody = sms.getDisplayMessageBody();
                //类型
                int status = sms.getStatus();//todo
                //时间
                Date date = new Date(sms.getTimestampMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                System.out.println("接受短信的时间: " + time);
            }
        }
    }
}
