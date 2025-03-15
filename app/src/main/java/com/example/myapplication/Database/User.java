package com.example.myapplication.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "code")
    int code;
    @ColumnInfo(name = "mobileNumber")
    String mobileNumber;

    @ColumnInfo(name = "filePath")
    String filePath;

//    public User(String name, String mobileNumber, int code) {
//        this.name = name;
//        this.mobileNumber = mobileNumber;
////        this.code = Integer.parseInt(UUID.randomUUID().toString());
//        this.code = code;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
