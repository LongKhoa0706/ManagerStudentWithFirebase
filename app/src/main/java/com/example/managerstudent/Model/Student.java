package com.example.managerstudent.Model;

public class Student {
    private String ten;
    private String thang;
    private String key;

    public Student(String ten, String thang, String key) {
        this.ten = ten;
        this.thang = thang;
        this.key = key;
    }

    public Student() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
