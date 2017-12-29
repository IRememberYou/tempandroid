package com.example.pinan.tempandroid.home.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.example.pinan.tempandroid.utils.HangUpTelephonyUtil;

/**
 * @author pinan
 * @date 2017/12/29
 */

/**
 * 电话来电去电显示监听
 */
public class PhoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        System.out.println("PhoneStateReceiver    :   " + action);
        
        String resultData = getResultData();
        System.out.println("  resultData:     :   " + resultData);
        
        if (Intent.ACTION_NEW_OUTGOING_CALL.equals(action)) {
            // 去电，可以用定时挂断
            // 双卡的手机可能不走这个Action
            String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            System.out.println("  number:     :   " + number);
        } else {
            // 来电去电都会走
            // 获取当前电话状态
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            System.out.println("  state:     :   " + state);
            //来电号码
            String extraIncomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            System.out.println("PhoneStateReceiver    :   " + extraIncomingNumber);
            
            if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                //10086来电了,就直接挂掉电话(做来电拦截)
                if ("10086".equals(extraIncomingNumber)) {
                    HangUpTelephonyUtil.endCall(context);
                    Toast.makeText(context, "电话挂断了", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
