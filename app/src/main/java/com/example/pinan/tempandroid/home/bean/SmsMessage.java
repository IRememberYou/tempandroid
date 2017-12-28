package com.example.pinan.tempandroid.home.bean;

/**
 * @author pinan
 * @date 2017/12/27
 */

public class SmsMessage {
    public String content;
    public String phone;
    public String type;//1:接受的短信; 2:发送的短信
    public String time;//时间
    public String number;//中心号码
    
    
    public SmsMessage(String content, String phone, String type, String time) {
        this.content = content;
        this.phone = phone;
        this.type = type;
        this.time = time;
    }
    
    @Override
    public String toString() {
        return "SmsMessage{" +
            "content='" + content + '\'' +
            ", phone='" + phone + '\'' +
            ", type='" + type + '\'' +
            ", time='" + time + '\'' +
            ", number='" + number + '\'' +
            '}';
    }
}
