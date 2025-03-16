package com.example.myapplication;

public class UserModel {
    public String name;
    public String mobileNumber;
    public int code;
    public long id;
    public String filePath;

    public UserModel(String name, String mobileNumber, int code, long id, String filePath) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.code = code;
        this.id = id;
        this.filePath = filePath;
    }
    public UserModel(){}

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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
