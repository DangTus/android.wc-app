package com.reddog.worldcup2022.module;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

public class DayModule {
    private int ngay;
    private int thang;
    private int nam;

    public DayModule(String date) {
        this.ngay = Integer.parseInt(date.split("-")[2]);
        this.thang = Integer.parseInt(date.split("-")[1]);
        this.nam = Integer.parseInt(date.split("-")[0]);
    }

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("00");
        String ngayStr = formatter.format(this.ngay);
        String thangStr = formatter.format(this.thang);

        return ngayStr + " - " + thangStr + " - " + this.nam;
    }
}
