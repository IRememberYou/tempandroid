package com.example.pinan.tempandroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.BaseActivity;
import com.example.pinan.tempandroid.home.adapter.SmsListAdapter;
import com.example.pinan.tempandroid.home.bean.SmsMessage;
import com.example.pinan.tempandroid.home.dialog.SmsDialogFramgent;
import com.example.pinan.tempandroid.utils.RecyclerViewItemClick;
import com.example.pinan.tempandroid.utils.SmsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author pinan
 * @date 2017/12/27
 */

public class ResultDealActivity extends BaseActivity {
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_result_deal);
        List<SmsMessage> sms = SmsUtil.findAll(this);
        //筛选不同人的短信且只获取一条短信 todo 继续优化
        List<SmsMessage> newSms = new ArrayList<>();
        for (int i = 0; i < sms.size(); i++) {
            //去重
            boolean flag = true;
            for (int j = 0; j < newSms.size(); j++) {
                flag = !newSms.get(j).phone.equals(sms.get(i).phone);
                if (!flag) {
                    break;
                }
            }
            if (flag) {
                newSms.add(sms.get(i));
            }
        }
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SmsListAdapter(newSms));
        
        recyclerView.addOnItemTouchListener(new RecyclerViewItemClick() {
            @Override
            protected void onItemClick(View view, int position) {
                SmsMessage item = ((SmsListAdapter) recyclerView.getAdapter()).getItem(position);
                //弹框 todo 将其封装通用
                SmsDialogFramgent.newInstace(item.phone)
                    .show(getSupportFragmentManager(), "smsdialogframgent");
            }
        });
    }
}