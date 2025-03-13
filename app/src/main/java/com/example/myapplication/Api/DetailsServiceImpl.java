package com.example.myapplication.Api;

import android.util.Log;

import com.example.myapplication.CallBack;
import com.example.myapplication.CallbackDetails;
import com.example.myapplication.UserListApiModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailsServiceImpl {
    UserDetailsModel userDetailsModel = null;
    public UserDetailsModel request(Long id, CallbackDetails callBack) {
        Log.d("asdDetails", " "+id);

        DetailsService detailsService = RetrofitManager.getClient().create(DetailsService.class);
        Call<DetailsResponse> call = detailsService.getUserDetails(id);

        call.enqueue(new Callback<DetailsResponse>() {
            @Override
            public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                DetailsResponse detailsResponse = response.body();
                Log.d("asdDetails", "api called ");

                if (response.code() == 200) {

                    if (detailsResponse != null) {
                    Log.d("asdDetails", "list not null");
                        userDetailsModel = new UserDetailsModel(detailsResponse.getFirstName(), detailsResponse.getAddress());
                    }
                    else {
                        Log.d("asdDetails", "" + response.errorBody());
                    }

                    callBack.onDetailsLoaded(userDetailsModel);
                } else {
                    Log.d("asdDetails", "" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<DetailsResponse> call, Throwable throwable) {
                Log.d("asdDetails", "api not called ");
            }
        });

        return userDetailsModel;
    }

//    public UserDetailsModel request(Long id) {
//        Log.d("asdDetails", " "+id);
//
//        DetailsService detailsService = RetrofitManager.getClient().create(DetailsService.class);
//        Call<DetailsResponse> call = detailsService.getUserDetails(id);
//
//        call.enqueue(new Callback<DetailsResponse>() {
//            @Override
//            public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
//                DetailsResponse detailsResponse = response.body();
//                Log.d("asdDetails", "api called ");
//
//                if (response.code() == 200) {
//
//                    if (detailsResponse != null) {
//                        Log.d("asdDetails", "list not null");
//                        userDetailsModel = new UserDetailsModel(detailsResponse.getFirstName(), detailsResponse.getAddress());
//
//                        Log.d("asdDetails", "name: "+userDetailsModel.getName()+" address: "+userDetailsModel.getAddress());
//                    }
//                    else {
//                        Log.d("asdDetails", "" + response.errorBody());
//                    }
//
////                    callBack.onDetailsLoaded(userDetailsModel);
//                } else {
//                    Log.d("asdDetails", "" + response.errorBody());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DetailsResponse> call, Throwable throwable) {
//                Log.d("asdDetails", "api not called ");
//            }
//        });
//
//        return userDetailsModel;
//    }
}
