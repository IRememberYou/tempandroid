package com.example.pinan.tempandroid.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.pinan.tempandroid.home.bean.SmsMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pinan
 * @date 2017/12/27
 */

/**
 * _id：短信序号，如100
 * thread_id：对话的序号，如100，与同一个手机号互发的短信，其序号是相同的
 * address：发件人地址，即手机号，如+86138138000
 * person：发件人，如果发件人在通讯录中则为具体姓名，陌生人为null
 * date：日期，long型，如1346988516，可以对日期显示格式进行设置
 * protocol：协议0SMS_RPOTO短信，1MMS_PROTO彩信
 * read：是否阅读0未读，1已读
 * status：短信状态-1接收，0complete,64pending,128failed
 * type：短信类型1是接收到的，2是已发出
 * body：短信具体内容
 * service_center：短信服务中心号码编号，如+8613800755500
 */
public class SmsUtil {
    public static List<SmsMessage> getSms(Context context) {
        List<SmsMessage> list = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        Cursor cursor = resolver.query(uri, null, null, null, "date desc");
        while (cursor.moveToNext()) {
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String time = cursor.getString(cursor.getColumnIndex("date"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            SmsMessage smsMessage = new SmsMessage(body, address, type, DateUtil.dateFormat(time));
            //这里我是要获取自己短信服务号码中的验证码~~
            Pattern pattern = Pattern.compile(" [a-zA-Z0-9]{10}");
            Matcher matcher = pattern.matcher(body);
            if (matcher.find()) {
                String res = matcher.group().substring(1, 11);
                smsMessage.number = res;
            }
            //
            list.add(smsMessage);
        }
        return list;
    }
}
