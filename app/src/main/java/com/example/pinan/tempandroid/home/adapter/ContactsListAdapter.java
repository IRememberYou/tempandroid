package com.example.pinan.tempandroid.home.adapter;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.QuickAdapter;
import com.example.pinan.tempandroid.home.bean.ContactsMessagge;

import java.util.List;

/**
 * @author pinan
 * @date 2017/12/28
 */

public class ContactsListAdapter extends QuickAdapter<ContactsMessagge> {
    
    public ContactsListAdapter(List<ContactsMessagge> list) {
        super(R.layout.item_contacts_list, list);
    }
    
    @Override
    protected void onBindViewHolder(ViewHolder holder, ContactsMessagge messagge) {
        if (messagge.phone.size() <= 0) {
            return;
        }
        holder.setText(R.id.tv_name, messagge.name)
            .setText(R.id.tv_phone, messagge.phone.get(0));
    }
}
