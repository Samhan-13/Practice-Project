package com.example.myapplication.ListFromDb;

public class UserModel {
    public String name;
    public String mobileNumber;
    public int code;
    public long id;

    public UserModel(String name, String mobileNumber, int code, long id) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.code = code;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
