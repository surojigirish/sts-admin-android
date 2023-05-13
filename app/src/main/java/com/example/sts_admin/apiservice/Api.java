package com.example.sts_admin.apiservice;

import com.example.sts_admin.Consts;
import com.example.sts_admin.apiservice.request.AddRequest;
import com.example.sts_admin.apiservice.request.DriverRegisterRequest;
import com.example.sts_admin.apiservice.request.HaltRequest;
import com.example.sts_admin.apiservice.request.ScheduleRequest;
import com.example.sts_admin.apiservice.response.AddResponse;
import com.example.sts_admin.apiservice.response.DriverRegisterResponse;
import com.example.sts_admin.apiservice.request.AdminLoginRequest;
import com.example.sts_admin.apiservice.response.AdminLoginResponse;
import com.example.sts_admin.apiservice.request.AdminLogoutRequest;
import com.example.sts_admin.apiservice.response.AdminLogoutResponse;
import com.example.sts_admin.apiservice.response.EmployeeDriverResponse;
import com.example.sts_admin.apiservice.response.HaltResponse;
import com.example.sts_admin.apiservice.response.RouteResponse;
import com.example.sts_admin.apiservice.response.ScheduleResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    @POST(Consts.ENDPOINT_ADMIN_LOGIN)
    Call<AdminLoginResponse> adminLogin(@Body AdminLoginRequest loginRequest);


    @HTTP(method = "DELETE", path = Consts.ENDPOINT_ADMIN_LOGOUT, hasBody = true)
    Call<AdminLogoutResponse> logout(@Body AdminLogoutRequest logoutRequest);

    @POST(Consts.ENDPOINT_REGISTER_DRIVER)
    Call<DriverRegisterResponse> driverRegister(
            @Header("Authorization") String token,
            @Body DriverRegisterRequest registerRequest);

    @GET(Consts.ENDPOINT_DRIVERS)
    Call<EmployeeDriverResponse> getDrivers(@Header("Authorization") String token);

    @POST(Consts.ENDPOINT_ADD_ROUTE)
    Call<AddResponse>addRoute(@Body AddRequest addRequest);

    @POST(Consts.ENDPOINT_ADD_HALT)
    Call<HaltResponse> addHalts(@Body HaltRequest haltRequest);

    @POST(Consts.ENDPOINT_ADD_SCHEDULE)
    Call<ScheduleResponse> addSchedule(@Body ScheduleRequest scheduleRequest);

    @GET(Consts.ENDPOINT_GET_ROUTE)
    Call<RouteResponse> getRoutesInfo();

}
