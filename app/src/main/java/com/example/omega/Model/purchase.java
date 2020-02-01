package com.example.omega.Model;

import com.orm.SugarRecord;

import java.util.Calendar;

public class purchase extends SugarRecord<purchase> {
    private cart c;
    private String pDate;

    public purchase() {
    }

    public purchase(cart c, String pDate) {
        this.c = c;
        this.pDate = pDate;
    }

    public cart getC() {
        return c;
    }

    public void setC(cart c) {
        this.c = c;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }
}
