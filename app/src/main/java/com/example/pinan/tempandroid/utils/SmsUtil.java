package com.example.pinan.tempandroid.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.pinan.tempandroid.home.bean.SmsMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pinan
 * @date 2017/12/27
 */

public class SmsUtil {
    /**
     * 查询所有的短信
     * <p>
     * 一些表字段的含义:
     * <p>
     * _id：短信序号，如100
     * thread_id：对话的序号，如100，与同一个手机号互发的短信，其序号是相同的
     * address：发件人地址，即手机号，如+86138138000
     * person：发件人，如果发件人在通讯录中则为具体姓名，陌生人为null
     * date：日期，long型，如1346988516，可以对日期显示格式进行设置
     * protocol：协议0SMS_RPOTO短信，1MMS_PROTO彩信
     * read：是否阅读0未读，1已读
     * status：短信状态,-1接收，0complete,64pending,128failed
     * type：短信类型,1是接收到的，2是已发出
     * body：短信具体内容
     * service_center：短信服务中心号码编号，如+8613800755500
     */
    public static List<SmsMessage> findAll(Context context) {
        List<SmsMessage> list = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        Cursor cursor = resolver.query(uri, null, null, null, "date desc");
        while (cursor.moveToNext()) {
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String time = cursor.getString(cursor.getColumnIndex("date"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            String read = cursor.getString(cursor.getColumnIndex("read"));
            SmsMessage smsMessage = new SmsMessage(body, address, type, DateUtil.dateFormat(time), read);
            list.add(smsMessage);
        }
        cursor.close();
        return list;
    }
    
    
    /**
     * 根据号码查询短信
     */
    public static List<SmsMessage> findByAddress(Context context, String address) {
        List<SmsMessage> list = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(Uri.parse("content://sms/"), null, "address =?", new String[]{address}, "date asc");
        while (cursor.moveToNext()) {
            String time = cursor.getString(cursor.getColumnIndex("date"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            String read = cursor.getString(cursor.getColumnIndex("read"));
            SmsMessage smsMessage = new SmsMessage(body, address, type, DateUtil.dateFormat(time), read);
            list.add(smsMessage);
        }
        cursor.close();
        return list;
    }
    
    /**
     * <p>
     * 发送短信,方法有两种,其一${SmsUtil.sendSms()},其二看${SmsUtil.doSendSMSTo()}
     * 其一可以监控发送状态和对方接收状态,因此使用的比较多
     * <p>
     * <p>
     * 公有方法详解：
     * <p>
     * <p>
     * <p>
     * ArrayList<String> divideMessage(String text)
     * 当短信超过SMS消息的最大长度时，将短信分割为几块。
     * 参数：text——初始的消息，不能为空
     * 返回值：有序的ArrayList<String>，可以重新组合为初始的消息
     * <p>
     * static SmsManager getDefault()
     * 获取SmsManager的默认实例。
     * 返回值：SmsManager的默认实例
     * <p>
     * void SendDataMessage(String destinationAddress, String scAddress, short destinationPort, byte[] data,PendingIntent sentIntent, PendingIntent deliveryIntent)
     * 发送一个基于SMS的数据到指定的应用程序端口。
     * 参数：
     * 1)、destinationAddress——消息的目标地址
     * 2)、scAddress——服务中心的地址or为空使用当前默认的SMSC 3)destinationPort——消息的目标端口号
     * 4)、data——消息的主体，即消息要发送的数据
     * 5)、sentIntent——如果不为空，当消息成功发送或失败这个PendingIntent就广播。结果代码是Activity.RESULT_OK表示成功，或RESULT_ERROR_GENERIC_FAILURE、RESULT_ERROR_RADIO_OFF、RESULT_ERROR_NULL_PDU之一表示错误。对应RESULT_ERROR_GENERIC_FAILURE，sentIntent可能包括额外的“错误代码”包含一个无线电广播技术特定的值，通常只在修复故障时有用。
     * 每一个基于SMS的应用程序控制检测sentIntent。如果sentIntent是空，调用者将检测所有未知的应用程序，这将导致在检测的时候发送较小数量的SMS。
     * 6)、deliveryIntent——如果不为空，当消息成功传送到接收者这个PendingIntent就广播。
     * 异常：如果destinationAddress或data是空时，抛出IllegalArgumentException异常。
     * <p>
     * void sendMultipartTextMessage(String destinationAddress, String scAddress, ArrayList<String> parts,ArrayList<PendingIntent> sentIntents, ArrayList<PendingIntent>  deliverIntents)
     * 发送一个基于SMS的多部分文本，调用者应用已经通过调用divideMessage(String text)将消息分割成正确的大小。
     * 参数：
     * 1)、destinationAddress——消息的目标地址
     * 2)、scAddress——服务中心的地址or为空使用当前默认的SMSC
     * 3)、parts——有序的ArrayList<String>，可以重新组合为初始的消息
     * 4)、sentIntents——跟SendDataMessage方法中一样，只不过这里的是一组PendingIntent
     * 5)、deliverIntents——跟SendDataMessage方法中一样，只不过这里的是一组PendingIntent
     * 异常：如果destinationAddress或data是空时，抛出IllegalArgumentException异常。
     * <p>
     * void sendTextMessage(String destinationAddress, String scAddress, String text, PendingIntent sentIntent,PendingIntent deliveryIntent)
     * 发送一个基于SMS的文本。参数的意义和异常前面的已存在的一样，不再累述。
     * <p>
     * <p>
     * <p>
     * 一些常量：
     * <p>
     * public static final int RESULT_ERROR_GENERIC_FAILURE:
     * 表示普通错误，值为1(0x00000001)
     * public static final int RESULT_ERROR_NO_SERVICE:
     * 表示服务当前不可用，值为4 (0x00000004)
     * public static final int RESULT_ERROR_NULL_PDU:
     * 表示没有提供pdu，值为3 (0x00000003)
     * public static final int RESULT_ERROR_RADIO_OFF:
     * 表示无线广播被明确地关闭，值为2 (0x00000002)
     * public static final int STATUS_ON_ICC_FREE:
     * 表示自由空间，值为0 (0x00000000)
     * public static final int STATUS_ON_ICC_READ:
     * 表示接收且已读，值为1 (0x00000001)
     * public static final int STATUS_ON_ICC_SENT:
     * 表示存储且已发送，值为5 (0x00000005)
     * public static final int STATUS_ON_ICC_UNREAD:
     * 表示接收但未读，值为3 (0x00000003)
     * public static final int STATUS_ON_ICC_UNSENT:
     * 表示存储但为发送，值为7 (0x00000007)
     * <p>
     * 原文: https://www.cnblogs.com/dongweiq/p/4866022.html
     * <p>
     * <p>
     * 权限添加:
     * <uses-permission android:name="android.permission.SEND_SMS" />
     */
    
    public static void sendSms(Context context, String phone, String message) {
        //处理返回的发送状态(发送报告部分)
        String SENT_SMS_ACTION = "SENT_SMS_ACTION";
        Intent sentIntent = new Intent(SENT_SMS_ACTION);
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent, 0);
        // register the Broadcast Receivers
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(context, "短信发送成功", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        break;
                    default:
                        break;
                }
            }
        }, new IntentFilter(SENT_SMS_ACTION));
        
        //处理返回的接收状态(接收报告部分)
        String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
        // create the deilverIntent parameter
        Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
        PendingIntent deliverPI = PendingIntent.getBroadcast(context, 0, deliverIntent, 0);
        // register the Broadcast Receivers
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "收信人已经成功接收", Toast.LENGTH_SHORT).show();
            }
        }, new IntentFilter(DELIVERED_SMS_ACTION));
        
        //真正发送短信的逻辑
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> divideContents = smsManager.divideMessage(message);
        for (String text : divideContents) {
            //如果不需要发送成功与否的报告,可以把后两个参数置 null
            smsManager.sendTextMessage(phone, null, text, sentPI, deliverPI);
        }
    }
    
    /**
     * 调起系统发短信功能
     */
    public static void doSendSMSTo(Context context, String phoneNumber, String message) {
        if (PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
            intent.putExtra("sms_body", message);
            context.startActivity(intent);
        }
    }
}
