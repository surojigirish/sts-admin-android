package com.example.sts_admin;

public class Consts {
    public static final String IP_ADDRESS = "192.168.232.169";

    // Base urls
    public static final String BASE_URL_ADMIN = "http://" + IP_ADDRESS + ":5000/employee/";
    public static final String BASE_URL_SCHEDULE = "http://" + IP_ADDRESS + ":5000/schedule/";

    // Endpoints
    public static final String ENDPOINT_ADMIN_LOGIN = "admin-login";
    public static final String ENDPOINT_ADMIN_LOGOUT = "logout";
    public static final String ENDPOINT_REGISTER_DRIVER = "register-driver";
    public static final String ENDPOINT_DRIVERS = "drivers";
    public static final String ENDPOINT_ADD_HALT = "add-halt";


    // Buttons
    public static final String BUTTON_SEARCH_BUS = "Search buses";


    // location
    public static final Integer LOCATION_THRESHOLD = 50;

    // request codes
    public static final Integer LOCATION_REQUEST_CODE = 1;
}
