package com.example.myapplication;

public class UserListApiModel {
    private String name;
    private String designation;
    private String mobileNumber;
    private long id;

    public UserListApiModel(String name, String designation, String mobileNumber, long id) {
        this.name = name;
        this.designation = designation;
        this.mobileNumber = mobileNumber;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
