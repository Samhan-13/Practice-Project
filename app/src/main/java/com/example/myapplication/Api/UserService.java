package com.example.myapplication.Api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("users")
    Call<UserListResponse> getusers();


}
