package com.example.pinan.tempandroid.home.bean;

/**
 * @author pinan
 * @date 2017/12/27
 */

public class SmsMessage {
    public String content;
    public String phone;
    public String type;//1:接收的短信; 2:发送的短信
    public String time;//时间
    public String read;//是否阅读0未读,1已读
    
    public SmsMessage(String content, String phone, String type, String time, String read) {
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
            '}';
    }
}
