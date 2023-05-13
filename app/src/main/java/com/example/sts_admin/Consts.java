package com.example.sts_admin;

public class Consts {
    public static final String IP_ADDRESS = "3.110.42.226";

    // Base urls
    public static final String BASE_URL_ADMIN = "http://" + IP_ADDRESS + "/employee/";
    public static final String BASE_URL_SCHEDULE = "http://" + IP_ADDRESS + "/schedule/";

    public static final String BASE_URL_BUS = "http://" + IP_ADDRESS + "/bus/";


    // Endpoints
    public static final String ENDPOINT_ADMIN_LOGIN = "admin-login";
    public static final String ENDPOINT_ADMIN_LOGOUT = "logout";
    public static final String ENDPOINT_REGISTER_DRIVER = "register-driver";
    public static final String ENDPOINT_DRIVERS = "drivers";

    public static final String ENDPOINT_BUS_INFO = "bus";

    public static final String ENDPOINT_ADD_HALT = "add-halt";
    public static final String ENDPOINT_ADD_SCHEDULE = "add-schedule";
    public static final String ENDPOINT_GET_ROUTE = "route";

    // bus
    public static final String ENDPOINT_ADD_BUS_DETAILS = "add-bus-details";
    public static final String ENDPOINT_ADD_BUS_SCHEDULE = "add-bus-schedule";

    //schedule
    public static final String ENDPOINT_ADD_SCHEDULE_DETAILS = "schedules";


    // Buttons
    public static final String BUTTON_SEARCH_BUS = "Search buses";


    // location
    public static final Integer LOCATION_THRESHOLD = 50;

    // request codes
    public static final Integer LOCATION_REQUEST_CODE = 1;
}
