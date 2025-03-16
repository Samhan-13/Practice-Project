package com.example.myapplication.Fragments;

import static android.app.Activity.RESULT_OK;
import static androidx.core.app.ActivityCompat.startActivityForResult;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.Database.User;
import com.example.myapplication.databinding.FragmentAddUserBinding;

import java.util.ArrayList;
import java.util.List;

public class AddUserViewModel extends ViewModel {

    private String name;
    private String mobileNumber;
    private  int code;
    private List<User> list = new ArrayList<>();
    private String filePath;

    private int PICK_IMAGE_REQUEST = 1;


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

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getPICK_IMAGE_REQUEST() {
        return PICK_IMAGE_REQUEST;
    }

    public void setPICK_IMAGE_REQUEST(int PICK_IMAGE_REQUEST) {
        this.PICK_IMAGE_REQUEST = PICK_IMAGE_REQUEST;
    }


}
