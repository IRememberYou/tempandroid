package com.example.pinan.tempandroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.BaseActivity;
import com.example.pinan.tempandroid.home.adapter.ContactsListAdapter;
import com.example.pinan.tempandroid.home.bean.ContactsMessagge;
import com.example.pinan.tempandroid.utils.PhoneUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @author pinan
 * @date 2017/12/28
 */

public class PhoneActivity extends BaseActivity {
    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        //获取联系人
        List<ContactsMessagge> contacts = PhoneUtil.getContacts(this);
        //recycleview 的配置
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ContactsListAdapter(contacts));
    }
}
