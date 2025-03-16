package com.example.myapplication.Fragments;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.ListFromApi.UserListApiModel;

import java.util.List;

public class ListApiViewModel extends ViewModel {
    private MutableLiveData<List<UserListApiModel>> list = new MutableLiveData<>();

    public MutableLiveData<List<UserListApiModel>> getList() {
        return list;
    }

    public void setList(List<UserListApiModel> list) {
        this.list.postValue(list);
    }
}
