package com.example.pinan.tempandroid.home.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.BaseDialogFragment;
import com.example.pinan.tempandroid.home.adapter.SmsAdapter;
import com.example.pinan.tempandroid.utils.SmsUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author pinan
 * @date 2017/12/28
 */

public class SmsDialogFramgent extends BaseDialogFragment {
    private static final String KEY_ADDRESS = "address";
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;
    @BindView(R.id.et_body)
    EditText etBody;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    private String address;
    
    public static SmsDialogFramgent newInstace(String address) {
        SmsDialogFramgent framgent = new SmsDialogFramgent();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ADDRESS, address);
        framgent.setArguments(bundle);
        return framgent;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomDatePickerDialog);
        address = getArguments().getString(KEY_ADDRESS);
        
    }
    
    @Override
    protected int getLayoutId() {
        return R.layout.dialog_fragment_sms;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置无标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setAttributes(params);
        
        tvAddress.setText(address);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(new SmsAdapter(SmsUtil.findByAddress(mContext, address)));
    }
    
    @Override
    public void onStart() {
        super.onStart();
        //设置fragment高度 、宽度
        int dialogHeight = (int) (mContext.getResources().getDisplayMetrics().heightPixels * 0.6);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, dialogHeight);
        getDialog().setCanceledOnTouchOutside(true);
    }
    
    @OnClick(R.id.tv_add)
    void onAddClick() {
        Toast.makeText(mContext, "增加符号", Toast.LENGTH_SHORT).show();
    }
    
    @OnClick(R.id.tv_send)
    void onSendClick() {
        String body = etBody.getText().toString();
        Toast.makeText(mContext, "发送短信,内容为:\n" + body, Toast.LENGTH_SHORT).show();
        SmsUtil.sendSms(mContext, address, body);
        etBody.setText("");
    }
}