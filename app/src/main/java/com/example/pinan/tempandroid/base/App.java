package com.example.pinan.tempandroid.base;

import android.app.Application;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.example.pinan.tempandroid.utils.PhoneUtil;

/**
 * @author pinan
 * @date 2017/12/29
 */

public class App extends Application {
    
    public static Context sContext;
    
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        //来电接听
        calllistener();
    }
    
    private void calllistener() {
        PhoneUtil.callListener(this, new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                System.out.println("电话的状态:  " + state);
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE:
                        Toast.makeText(App.this, "电话挂断了", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Toast.makeText(App.this, " 来电接通 或者 去电，去电接通  但是没法区分", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        Toast.makeText(App.this, "你的电话来了", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
