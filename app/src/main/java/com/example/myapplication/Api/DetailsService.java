package com.example.myapplication.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DetailsService {
    @GET("users/{id}")
    Call<DetailsResponse> getUserDetails(@Path(value = "id") Long id);
}
