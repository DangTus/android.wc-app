package com.reddog.worldcup2022.model;

import java.io.Serializable;

public class BangDau implements Serializable {
    private String id;
    private String ten_quoc_gia;
    private int hinh_anh;

    public BangDau() {
    }

    public BangDau(String id, String ten_quoc_gia, int hinh_anh) {
        this.id = id;
        this.ten_quoc_gia = ten_quoc_gia;
        this.hinh_anh = hinh_anh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen_quoc_gia() {
        return ten_quoc_gia;
    }

    public void setTen_quoc_gia(String ten_quoc_gia) {
        this.ten_quoc_gia = ten_quoc_gia;
    }

    public int getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(int hinh_anh) {
        this.hinh_anh = hinh_anh;
    }
}
