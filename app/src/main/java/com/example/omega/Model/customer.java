package com.example.omega.Model;

import com.orm.SugarRecord;

public class customer extends SugarRecord<customer>{
    private String username,email,phoneNumber,password;

    public customer() {
    }

    public customer(String username,String email,String phoneNumber,String password) {
        this.username = username;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.password=password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
