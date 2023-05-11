package com.example.sts_admin.apiservice;

<<<<<<< HEAD
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
=======
import com.example.sts_admin.apiservice.request.DriverRegisterRequest;
import com.example.sts_admin.apiservice.response.DriverRegisterResponse;
import com.example.sts_admin.apiservice.request.AdminLoginRequest;
import com.example.sts_admin.apiservice.response.AdminLoginResponse;
import com.example.sts_admin.apiservice.request.AdminLogoutRequest;
import com.example.sts_admin.apiservice.response.AdminLogoutResponse;
import com.example.sts_admin.apiservice.response.EmployeeDriverResponse;
>>>>>>> 7edc965b6fe8bd6df472f9f546f1113e51f3d1de

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    @POST("admin-login")
    Call<AdminLoginResponse> adminLogin(@Body AdminLoginRequest loginRequest);


    @HTTP(method = "DELETE", path = "logout", hasBody = true)
    Call<AdminLogoutResponse> logout(@Body AdminLogoutRequest logoutRequest);

    @POST("register-driver")
    Call<DriverRegisterResponse> driverRegister(
            @Header("Authorization") String token,
            @Body DriverRegisterRequest registerRequest);

    @GET("drivers")
    Call<EmployeeDriverResponse> getDrivers(@Header("Authorization") String token);


    @POST("add-route")
    Call<AddResponse> addRoute(@Body AddRequest addRequest);

    @POST("add-halt")
    Call<SourceResponse>addSource(@Body SourceRequest sourceRequest);


}
