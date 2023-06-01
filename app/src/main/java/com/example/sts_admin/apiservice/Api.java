package com.example.sts_admin.apiservice;

import com.example.sts_admin.Consts;

import com.example.sts_admin.apiservice.request.AddBusRequest;
import com.example.sts_admin.apiservice.request.AddBusScheduleRequest;
import com.example.sts_admin.apiservice.request.DriverLoginRequest;
import com.example.sts_admin.apiservice.request.LocationUpdate;
import com.example.sts_admin.apiservice.request.RouteRequest;
import com.example.sts_admin.apiservice.request.DriverRegisterRequest;
import com.example.sts_admin.apiservice.request.HaltRequest;
import com.example.sts_admin.apiservice.request.RouteInfoRequest;
import com.example.sts_admin.apiservice.request.ScheduleRequest;
import com.example.sts_admin.apiservice.request.ValidationRequest;
import com.example.sts_admin.apiservice.response.AddBusResponse;
import com.example.sts_admin.apiservice.response.BusScheduleDetailsResponse;
import com.example.sts_admin.apiservice.response.DriverLoginResponse;
import com.example.sts_admin.apiservice.response.MainResponse;
import com.example.sts_admin.apiservice.response.RouteResponse;
import com.example.sts_admin.apiservice.response.BusScheduleResponse;
import com.example.sts_admin.apiservice.response.DriverRegisterResponse;
import com.example.sts_admin.apiservice.request.AdminLoginRequest;
import com.example.sts_admin.apiservice.response.AdminLoginResponse;
import com.example.sts_admin.apiservice.request.AdminLogoutRequest;
import com.example.sts_admin.apiservice.response.AdminLogoutResponse;
import com.example.sts_admin.apiservice.response.EmployeeDriverResponse;
import com.example.sts_admin.apiservice.response.HaltResponse;
import com.example.sts_admin.apiservice.response.RouteInfoResponse;
import com.example.sts_admin.apiservice.response.ScheduleResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @POST(Consts.ENDPOINT_ADMIN_LOGIN)
    Call<AdminLoginResponse> adminLogin(@Body AdminLoginRequest loginRequest);

    @POST(Consts.ENDPOINT_DRIVER_LOGIN)
    Call<DriverLoginResponse> driverLogin(@Body DriverLoginRequest driverLoginRequest);

    @HTTP(method = "DELETE", path = Consts.ENDPOINT_ADMIN_LOGOUT, hasBody = true)
    Call<AdminLogoutResponse> logout(@Body AdminLogoutRequest logoutRequest);

    @POST(Consts.ENDPOINT_REGISTER_DRIVER)
    Call<DriverRegisterResponse> driverRegister(
            @Header("Authorization") String token,
            @Body DriverRegisterRequest registerRequest);

    @GET(Consts.ENDPOINT_DRIVERS)
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

    @POST(Consts.ENDPOINT_ADD_BUS_DETAILS)
    Call<AddBusResponse> addBus(@Body AddBusRequest addBusRequest);


    @GET(Consts.ENDPOINT_BUS_INFO)
    Call<BusScheduleResponse> getAllBus();

    @POST(Consts.ENDPOINT_ADD_SCHEDULE)
    Call<ScheduleResponse> addSchedule(@Body ScheduleRequest scheduleRequest);

    @GET(Consts.ENDPOINT_ADD_SCHEDULE_DETAILS)
    Call<ScheduleResponse> getAllSchedule();

    @GET(Consts.ENDPOINT_GET_ROUTE)
    Call<RouteResponse> getAllRoutes();

    @POST(Consts.ENDPOINT_ADD_BUS_SCHEDULE)
    Call<BusScheduleResponse> addBusSchedule(@Body AddBusScheduleRequest addBusScheduleRequest);


    @GET(Consts.ENDPOINT_BUS_SCHEDULE_LIST_ITEMS)
    Call<BusScheduleDetailsResponse> getAllBusScheduleList();


    // Bus-schedule location update api
    @PUT("bus-schedule/{bus-schedule-id}/update-location")
    Call<Void> updateLocation(
            @Path("bus-schedule-id") Integer busScheduleId,
            @Body LocationUpdate request );

    @GET("search")
    Call<MainResponse> getBusScheduleOnDate(@Query("date") String date);

    @POST("passenger/{passenger-id}/passes/{pass-id}/validate-pass")
    Call<MainResponse> validatePass(
            @Path("passenger-id") Integer passengerId,
            @Path("pass-id") Integer passId,
            @Body ValidationRequest request );

    // Driver bus-schedules
    @GET(Consts.ENDPOINT_DRIVER_BUS_SCHEDULE)
    Call<MainResponse> driverBusSchedules(@Path("employee-id") Integer empId);
}
