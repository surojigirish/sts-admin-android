package com.example.sts_admin.apiservice;

import com.example.sts_admin.Consts;
import com.example.sts_admin.apiservice.request.DriverRegisterRequest;
import com.example.sts_admin.apiservice.request.HaltRequest;
import com.example.sts_admin.apiservice.request.RouteInfoRequest;
import com.example.sts_admin.apiservice.request.RouteRequest;
import com.example.sts_admin.apiservice.response.DriverRegisterResponse;
import com.example.sts_admin.apiservice.request.AdminLoginRequest;
import com.example.sts_admin.apiservice.response.AdminLoginResponse;
import com.example.sts_admin.apiservice.request.AdminLogoutRequest;
import com.example.sts_admin.apiservice.response.AdminLogoutResponse;
import com.example.sts_admin.apiservice.response.EmployeeDriverResponse;
import com.example.sts_admin.apiservice.response.HaltResponse;
import com.example.sts_admin.apiservice.response.RouteInfoResponse;
import com.example.sts_admin.apiservice.response.RouteResponse;
import com.example.sts_admin.model.Route;

import java.util.List;

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

    @POST(Consts.ENDPOINT_ADD_HALT)
    Call<HaltResponse> addHalts(@Body HaltRequest haltRequest);

    @POST(Consts.ENDPOINT_ADD_ROUTE_INFO)
    Call<RouteInfoResponse> addRouteInfo(@Body RouteInfoRequest routeInfoRequest);

    @POST(Consts.ENDPOINT_ADD_ROUTE)
    Call<RouteResponse> addRoute(@Body RouteRequest routeRequest);

    @GET(Consts.ENDPOINT_GET_ROUTE)
    Call<RouteResponse> getRoutes();

    @GET(Consts.ENDPOINT_GET_HALTS)
    Call<HaltResponse> getAllHalts();

}
