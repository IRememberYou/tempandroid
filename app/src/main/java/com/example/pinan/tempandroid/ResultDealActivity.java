package com.example.pinan.tempandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.pinan.tempandroid.base.BaseActivity;
import com.example.pinan.tempandroid.home.bean.SmsMessage;
import com.example.pinan.tempandroid.utils.SmsUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @author pinan
 * @date 2017/12/27
 */

public class ResultDealActivity extends BaseActivity {
    @BindView(R.id.tv_test)
    TextView tvTest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_deal);
        List<SmsMessage> sms = SmsUtil.getSms(this);
        if (sms != null) {
            for (SmsMessage sm : sms) {
                System.out.println(sm.toString());
                System.out.println("----------------");
            }
        }
    }
}
