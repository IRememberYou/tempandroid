package com.example.pinan.tempandroid.home.bean;

import java.util.List;

/**
 * @author pinan
 * @date 2017/12/28
 */

public class ContactsMessagge {
    public String name;
    public List<String> phone;
    
    public ContactsMessagge(String name, List<String> phone) {
        this.name = name;
        this.phone = phone;
    }
}
