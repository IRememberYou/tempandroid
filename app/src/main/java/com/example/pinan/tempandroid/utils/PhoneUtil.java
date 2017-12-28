package com.example.pinan.tempandroid.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.example.pinan.tempandroid.home.bean.ContactsMessagge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pinan
 * @date 2017/12/28
 */
public class PhoneUtil {
    /**
     * 获取联系人(电话,姓名)
     * <p>
     * 涉及到多表
     * <p>
     * 权限添加:
     * <uses-permission android:name="android.permission.WRITE_CONTACTS"/>
     * <uses-permission android:name="android.permission.READ_CONTACTS"/>
     * <uses-permission android:name="android.permission.GET_ACCOUNTS"/>
     */
    public static List<ContactsMessagge> getContacts(Context context) {
        List<ContactsMessagge> list = new ArrayList<>();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //指定获取_id和display_name两列数据，display_name即为姓名
        String[] projection = new String[]{
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
        };
        //根据Uri查询相应的ContentProvider，cursor为获取到的数据集
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        
        while (cursor != null && cursor.moveToNext()) {
            //id
            Long id = cursor.getLong(0);
            //获取姓名
            String name = cursor.getString(1);
            
            //指定获取NUMBER这一列数据
            String[] phoneProjection = new String[]{
                ContactsContract.CommonDataKinds.Phone.NUMBER
            };
            
            //根据联系人的ID获取此人的电话号码
            Uri contentUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            String contactId = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
            Cursor phonesCusor = context.getContentResolver()
                .query(contentUri, phoneProjection, contactId + " = " + id, null, null);
            
            //一个人可有有多个电话
            List<String> phones = new ArrayList<>();
            //因为每个联系人可能有多个电话号码，所以需要遍历
            while (phonesCusor != null && phonesCusor.moveToNext()) {
                //电话号码
                String num = phonesCusor.getString(0);
                phones.add(num);
            }
            list.add(new ContactsMessagge(name, phones));
            phonesCusor.close();
        }
        cursor.close();
        return list;
    }
    
    
    /**
     * 拨打的电话,拨出的电话,未接电话(通话记录)
     */
    
    /**
     * 来电监听
     */
    
    /**
     * 打电话
     */
}
