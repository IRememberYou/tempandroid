package com.example.pinan.tempandroid.home.adapter;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.QuickAdapter;
import com.example.pinan.tempandroid.home.bean.CallHistory;
import com.example.pinan.tempandroid.utils.DateUtil;

import java.util.List;

/**
 * @author pinan
 * @date 2017/12/29
 */

public class CallHistoryAdapter extends QuickAdapter<CallHistory> {
    public CallHistoryAdapter(List<CallHistory> list) {
        super(R.layout.item_call_history, list);
    }
    
    @Override
    protected void onBindViewHolder(ViewHolder holder, CallHistory callHistory) {
        holder.setText(R.id.tv_name, callHistory.name == null ? callHistory.number : callHistory.name)
            .setText(R.id.tv_time, phoneDate(callHistory.date))
            .setText(R.id.tv_duration, phoneDuraction(callHistory.duration))
            .setText(R.id.tv_type, phoneType(callHistory.type));
    }
    
    private String phoneType(String type) {
        String typeStr = "";
        switch (Integer.parseInt(type)) {
            case 1:
                //来电
                typeStr = "(来电)";
                break;
            case 2:
                //拨入
                typeStr = "(拨打)";
                break;
            case 3:
                //未接
                typeStr = "(未接)";
                break;
            case 5:
                //挂断
                typeStr = "(拒接)";
            default:
                break;
        }
        return typeStr;
    }
    
    private String phoneDate(String date) {
        String str = DateUtil.dateFormat(date);
        int len = str.length();
        String substring = str.substring(len - 8, len);
        return substring;
    }
    
    private String phoneDuraction(String duration) {
        String durationStr = "";
        int newDuration = Integer.parseInt(duration);
        if (newDuration < 60) {
            durationStr = "00:" + newDuration;
        } else {
            durationStr = newDuration / 60 + ":" + newDuration % 60;
        }
        return durationStr;
    }
}
