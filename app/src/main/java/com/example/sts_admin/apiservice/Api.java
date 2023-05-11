package com.example.sts_admin.apiservice;

import com.example.sts_admin.addRouteModel.AddRequest;
import com.example.sts_admin.addRouteModel.AddResponse;
import com.example.sts_admin.addSourceModel.SourceRequest;
import com.example.sts_admin.addSourceModel.SourceResponse;
import com.example.sts_admin.driverRegistrationModel.RegisterRequest;
import com.example.sts_admin.driverRegistrationModel.RegisterResponse;
import com.example.sts_admin.loginModel.LoginRequest;
import com.example.sts_admin.loginModel.LoginResponse;
import com.example.sts_admin.logoutModel.LogoutRequest;
import com.example.sts_admin.logoutModel.LogoutResponse;
import com.example.sts_admin.model.EmployeeDriverResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @POST("admin-login")
    Call<LoginResponse> adminLogin(@Body LoginRequest loginRequest);


    @HTTP(method = "DELETE", path = "logout", hasBody = true)
    Call<LogoutResponse> logout(@Body LogoutRequest logoutRequest);

    @POST("register-driver")
    Call<RegisterResponse> driverRegister(
            @Header("Authorization") String token,
            @Body RegisterRequest registerRequest);

    @GET("drivers")
    Call<EmployeeDriverResponse> getDrivers(@Header("Authorization") String token);


    @POST("add-route")
    Call<AddResponse> addRoute(@Body AddRequest addRequest);

    @POST("add-halt")
    Call<SourceResponse>addSource(@Body SourceRequest sourceRequest);


}
