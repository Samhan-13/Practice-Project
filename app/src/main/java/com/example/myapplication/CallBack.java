package com.example.myapplication;

import com.example.myapplication.ListFromApi.UserListApiModel;

import java.util.List;

public interface CallBack {
    public void onDataLoaded(List<UserListApiModel> data);
}
