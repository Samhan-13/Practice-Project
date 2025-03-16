package com.example.myapplication.Fragments;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.Database.User;
import com.example.myapplication.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserListFromDbViewModel extends ViewModel {
    private List<UserModel> userList = new ArrayList<>();
    private List<User> userListFromDb = new ArrayList<>();

    public List<UserModel> getUserList() {
        return userList;
    }

    public void setUserList(List<UserModel> userList) {
        this.userList = userList;
    }

    public List<User> getUserListFromDb() {
        return userListFromDb;
    }

    public void setUserListFromDb(List<User> userListFromDb) {
        this.userListFromDb = userListFromDb;
    }
}
