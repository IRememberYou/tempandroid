package com.example.pinan.tempandroid.home.bean;

/**
 * @author pinan
 * @date 2017/12/29
 */

public class CallHistory {
    public String name;
    public String number;
    public String type;//1:来电,2:拨出电话
    public String date;//
    public String duration;//通话时长,单位为秒
    
    public CallHistory(String name, String number, String type, String date, String duration) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.date = date;
        this.duration = duration;
    }
    
    @Override
    public String toString() {
        return "CallHistory{" +
            "name='" + name + '\'' +
            ", number='" + number + '\'' +
            ", type='" + type + '\'' +
            ", date='" + date + '\'' +
            ", duration='" + duration + '\'' +
            '}';
    }
}
