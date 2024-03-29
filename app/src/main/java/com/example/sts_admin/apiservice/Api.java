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
import com.example.sts_admin.apiservice.request.UpdateBusDetailsRequest;
import com.example.sts_admin.apiservice.request.UpdateDriverRequest;
import com.example.sts_admin.apiservice.request.UpdateScheduleRequest;
import com.example.sts_admin.apiservice.request.ValidationRequest;
import com.example.sts_admin.apiservice.response.AddBusResponse;
import com.example.sts_admin.apiservice.response.BusScheduleDetailsResponse;
import com.example.sts_admin.apiservice.response.DriverLoginResponse;
import com.example.sts_admin.apiservice.response.GetBusResponse;
import com.example.sts_admin.apiservice.response.GetPassengerDetailResponse;
import com.example.sts_admin.apiservice.response.GetRouteInfoResponse;
import com.example.sts_admin.apiservice.response.GetRouteResponse;
import com.example.sts_admin.apiservice.response.MainResponse;
import com.example.sts_admin.apiservice.response.OnBoardPassengerResponse;
import com.example.sts_admin.apiservice.response.PassengerProfileImageResponse;
import com.example.sts_admin.apiservice.response.ReportGenerationResponse;
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
import com.example.sts_admin.apiservice.response.RouteRoutesInfoResponse;
import com.example.sts_admin.apiservice.response.RouteScheduleResponse;
import com.example.sts_admin.apiservice.response.ScheduleResponse;
import com.example.sts_admin.apiservice.response.UpdateBusDetailsResponse;
import com.example.sts_admin.apiservice.response.UpdateDriverDetailsResponse;
import com.example.sts_admin.apiservice.response.UpdateScheduleResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @HTTP(method = "DELETE", path = Consts.ENDPOINT_DELETE_ROUTE)
    Call<RouteResponse> deleteRoute(@Path("route_id") Integer routeId);

    @HTTP(method = "DELETE", path = Consts.ENDPOINT_DELETE_ROUTE_INFO)
    Call<RouteInfoResponse> deleteRouteInfo(@Path("route_info_id") Integer routeInfoId);

    @PUT(Consts.ENDPOINT_UPDATE_BUS_DETAILS)
    Call<UpdateBusDetailsResponse> updateBusDetails(@Path("bus_id")Integer busDetailId,
                                                    @Body UpdateBusDetailsRequest updateBusDetailsRequest);

    @GET(Consts.ENDPOINT_GET_ROUTE_INFO)
    Call<RouteResponse> getRoutesInfo();

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


    @GET(Consts.ENDPOINT_GET_ROUTE)
    Call<GetRouteResponse> getAllRouteList();

    @GET(Consts.ENDPOINT_BUS_INFO)
    Call<GetBusResponse> getAllBusList();

    @GET(Consts.ENDPOINT_GET_ROUTE_INFO)
    Call<GetRouteInfoResponse> getAllRouteInfoDetails();

    @GET(Consts.ENDPOINT_GET_ROUTE_ROUTES_INFO)
    Call<RouteRoutesInfoResponse> routeRoutesInfo(@Path("route_id") int routeId);

    @GET(Consts.ENDPOINT_GET_PASSENGER_DETAILS)
    Call<GetPassengerDetailResponse> getAllPassengerDetails();
    @GET(Consts.ENDPOINT_GET_ONBOARD_PASSENGER)
    Call<OnBoardPassengerResponse> getPassengerDeatails(@Path("bus_schedule_id") Integer bus_schedule_id);

    @GET(Consts.ENDPOINT_GENERATE_BUS_REPORT)
    Call<ReportGenerationResponse> getBusReport(@Query("bus-id") int busId, @Query("date") String date);

    @GET(Consts.ENDPOINT_GENERATE_BUS_REPORT_ENDDATE)
    Call<ReportGenerationResponse> getBusReportEndDate(@Query("bus-id") int busId,
                                                       @Query("start-date") String startDate,
                                                       @Query("end-date") String endDate);
    @GET(Consts.ENDPOINT_SHOW_ROUTE_SCHEDULE_DETAILS)
    Call<RouteScheduleResponse> showRouteSchedule(@Path("route-id") Integer routeId);
    @GET(Consts.BASE_URL_GET_PASSENGER_PROFILE)
    Call<PassengerProfileImageResponse> getPassengerPrOfileImage(@Path("url") String url);


    @PUT(Consts.ENDPOINT_UPDATE_SCHEDULE_DETAILS)
    Call<UpdateScheduleResponse> updateScheduleDetails(@Path("schedule-id")Integer scheduleId,
                                                  @Body UpdateScheduleRequest updateScheduleRequest);

    @PUT(Consts.ENDPOINT_UPDATE_DRIVER_DETAILS)
    Call<UpdateDriverDetailsResponse> updateDriverDetails(@Path("driver-id")Integer driverId,
                                                            @Body UpdateDriverRequest updateDriverRequest,
                                                            @Header("Authorization") String token);
}
