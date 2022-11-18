package com.reddog.worldcup2022.module;

import java.util.Date;

public class DateModule {
    private int ngay;
    private int thang;
    private int nam;

    public DateModule(String date) {
        this.ngay = Integer.parseInt(date.split("-")[2]);
        this.thang = Integer.parseInt(date.split("-")[1]);
        this.nam = Integer.parseInt(date.split("-")[0]);
    }

    @Override
    public String toString() {
        return this.ngay + "-" + this.thang + "-" + this.nam;
    }

    public String getStringDate() {
        return this.ngay + "-" + this.thang + "-" + this.nam;
    }
}
