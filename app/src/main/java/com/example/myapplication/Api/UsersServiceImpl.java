package com.example.myapplication.Api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.CallBack;
import com.example.myapplication.UserListApiModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersServiceImpl {
    List<UserListApiModel> info = new ArrayList<>();

    public List<UserListApiModel> request(CallBack callBack) {

        UserService userService = RetrofitManager.getClient().create(UserService.class);

        Log.d("asddata", "api called");

        Call<UserListResponse> call = userService.getusers();
        call.enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserListResponse> call, @NonNull Response<UserListResponse> response) {

                UserListResponse userListResponse = response.body();

                if (response.code() == 200) {
                    if (userListResponse != null) {
//                    Log.d("asddata", "list not null");
                    }
                    for (UserListResponse.User u : userListResponse.getUser()) {
                        Log.d("asddata", "name: " + u.getName() + " id: " + u.getId() + " title: " + u.getCompany().getTitle() + " number: " + u.getPhoneNumber()+
                                " maidenName: "+u.getMaidenName());
                        info.add(new UserListApiModel(u.getName(), u.getCompany().getTitle(), u.getPhoneNumber(), u.getId(), u.getMaidenName()));

                    }
                    Log.d("asddata", "" + info.size());
                    callBack.onDataLoaded(info);
                } else {
                    Log.d("asddata", "" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable throwable) {
                Log.d("asddata", "" + throwable.getMessage());
            }
        });
        Log.d("asddata", "" + info.size());
        return info;

    }
}
