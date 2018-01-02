package com.example.pinan.tempandroid.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.example.pinan.tempandroid.home.bean.CallHistory;
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
    public static List<CallHistory> getCallHistoryList(Context context) {
        List<CallHistory> list = new ArrayList<>();
        
        ContentResolver resolver = context.getContentResolver();
        //联系人 uri
        Uri uri = CallLog.Calls.CONTENT_URI;
        //对应的是名字,号码,通话类型(呼入,呼出,未接),时间,通话时长
        String[] projection = new String[]{
            CallLog.Calls.CACHED_NAME,
            CallLog.Calls.NUMBER,
            CallLog.Calls.TYPE,
            CallLog.Calls.DATE,
            CallLog.Calls.DURATION
        };
        //查询语句
        Cursor cursor = resolver.query(uri,
            projection, null, null, "date desc");
        while (cursor != null && cursor.moveToNext()) {
            String callName = cursor.getString(0);
            String callNum = cursor.getString(1);
            String type = cursor.getString(2);
            String date = cursor.getString(3);
            String duration = cursor.getString(4);
            
            //如果名字为空,去通讯录中查询是否有对应得联系人
            if (TextUtils.isEmpty(callName)) {
                //设置查询条件
                Cursor query = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME},
                    ContactsContract.CommonDataKinds.Phone.NUMBER + " = ?",
                    new String[]{callNum},
                    null);
                while (query != null & query.moveToNext()) {
                    callName = query.getString(0);
                    query.close();
                    break;
                }
            }
            list.add(new CallHistory(callName, callNum, type, date, duration));
        }
        cursor.close();
        return list;
    }
    
    /**
     * 来电监听(方式有多种,这里介绍两种)
     * 其一:{@link #callListener}
     * 其二:则是用广播({@link com.example.pinan.tempandroid.home.receiver.PhoneStateReceiver})
     * <p>
     * 其一的使用方式:
     *
     * @param listener 一般是继承PhoneStateListener类,重写方法onCallStateChanged.
     *                 onCallStateChanged方法中的参数1(state)
     *                 state 的含义:
     *                 1,TelephonyManager.CALL_STATE_IDLE  //电话挂断了
     *                 2,TelephonyManager.CALL_STATE_RINGING //来电响铃
     *                 3,TelephonyManager.CALL_STATE_OFFHOOK //来电接通 或者 去电，去电接通  但是没法区分
     */
    public static void callListener(Context context, PhoneStateListener listener) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }
    
    /**
     * 打电话
     * 权限:
     * <uses-permission android:name="android.permission.CALL_PHONE" />
     */
    
    public static void call(Context context, String number) {
        //方式一:跳过拨号界面，直接拨打电话(为了提高用户体验度,最好调用下面的方法时添加一个提示按钮, 比如电话是否拨打)
//        Intent intent1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
//        context.startApp(intent1);
        //方式二:调用拨打界面拨打
        Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent2);
    }
}
