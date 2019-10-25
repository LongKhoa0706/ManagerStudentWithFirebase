package com.example.managerstudent.Model;

public class Lop {
    private String  maLop;
    private String tenLop;
    private String key;


    public Lop() {
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Lop(String maLop, String tenLop, String key) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.key = key;
    }
}
