package com.example.pinan.tempandroid.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.BaseFragemnt;
import com.example.pinan.tempandroid.home.adapter.CallHistoryAdapter;
import com.example.pinan.tempandroid.home.adapter.ContactsListAdapter;
import com.example.pinan.tempandroid.home.bean.CallHistory;
import com.example.pinan.tempandroid.home.bean.ContactsMessagge;
import com.example.pinan.tempandroid.utils.PhoneUtil;
import com.example.pinan.tempandroid.utils.RecyclerViewItemClick;

import java.util.List;

import butterknife.BindView;

/**
 * @author pinan
 * @date 2017/12/29
 */
public class PhoneListFragment extends BaseFragemnt {
    private static final String KEY_TAG = "tag";
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;
    
    public static PhoneListFragment newInstance(String tag) {
        PhoneListFragment fragment = new PhoneListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TAG, tag);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    protected int getLayoutResId() {
        return R.layout.common_recycle_view;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //联系人,通话记录
        String tag = getArguments().getString(KEY_TAG);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        if (tag.equals("联系人")) {
            recyclerView.setAdapter(new ContactsListAdapter(PhoneUtil.getContacts(mContext)));
        } else if (tag.equals("通话记录")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<CallHistory> callHistoryList = PhoneUtil.getCallHistoryList(mContext);
                    Message message = new Message();
                    message.obj = callHistoryList;
                    mHandler.sendMessage(message);
                }
            }).start();
//            recyclerView.setAdapter(new CallHistoryAdapter(PhoneUtil.getCallHistoryList(mContext)));
        }
        
        recyclerView.addOnItemTouchListener(new RecyclerViewItemClick() {
            @Override
            protected <T extends RecyclerView.Adapter> void onItemClick(T adapter, View view, int position) {
                if (adapter instanceof ContactsListAdapter) {
                    Toast.makeText(mContext, "联系人", Toast.LENGTH_SHORT).show();
                    ContactsMessagge item = ((ContactsListAdapter) adapter).getItem(position);
                    PhoneUtil.call(mContext, item.phone.get(0));
                } else if (adapter instanceof CallHistoryAdapter) {
                    Toast.makeText(mContext, "通话记录", Toast.LENGTH_SHORT).show();
                    CallHistory item = ((CallHistoryAdapter) adapter).getItem(position);
                    PhoneUtil.call(mContext, item.number);
                }
            }
        });
    }
    
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<CallHistory> callHistoryList = (List<CallHistory>) msg.obj;
            if (recyclerView == null) {
                return;
            }
            recyclerView.setAdapter(new CallHistoryAdapter(callHistoryList));
        }
    };
}
