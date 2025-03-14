package com.example.myapplication.ListFromApi;

public class UserListApiModel {
    private String name;
    private String designation;
    private String mobileNumber;
    private long id;
    private String maidenName;

    public UserListApiModel(String name, String designation, String mobileNumber, long id, String maidenName) {
        this.name = name;
        this.designation = designation;
        this.mobileNumber = mobileNumber;
        this.id = id;
        this.maidenName = maidenName;
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

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }
}
