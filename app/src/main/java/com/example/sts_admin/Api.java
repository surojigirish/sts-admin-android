package com.example.sts_admin;

import com.example.sts_admin.loginModel.LoginRequest;
import com.example.sts_admin.loginModel.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("admin-login")
    Call<LoginResponse> adminLogin(@Body LoginRequest loginRequest);

}
