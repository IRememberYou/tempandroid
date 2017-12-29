package com.example.pinan.tempandroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.BaseFragemnt;
import com.example.pinan.tempandroid.home.adapter.CallHistoryAdapter;
import com.example.pinan.tempandroid.home.adapter.ContactsListAdapter;
import com.example.pinan.tempandroid.utils.PhoneUtil;

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
            recyclerView.setAdapter(new CallHistoryAdapter(PhoneUtil.getCallHistoryList(mContext)));
        }
    }
}
