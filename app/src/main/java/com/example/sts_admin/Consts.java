package com.example.sts_admin;

public class Consts {
    public static final String IP_ADDRESS = "3.110.42.226";
//    public static final String IP_ADDRESS = "192.168.232.166:5000";

    // Base urls
    public static final String BASE_URL_ADMIN = "http://" + IP_ADDRESS + "/employee/";
    public static final String BASE_URL_SCHEDULE = "http://" + IP_ADDRESS + "/schedule/";

    public static final String BASE_URL_BOOKING = "http://" + IP_ADDRESS + "/booking/";

    public static final String BASE_URL_BUS = "http://" + IP_ADDRESS + "/bus/";

    public static final String BASE_URL_LOCATION = "http://" + IP_ADDRESS + "/location/";

    // Endpoints
    public static final String ENDPOINT_ADMIN_LOGIN = "admin-login";
    public static final String ENDPOINT_DRIVER_LOGIN = "driver-login";
    public static final String ENDPOINT_ADMIN_LOGOUT = "logout";
    public static final String ENDPOINT_REGISTER_DRIVER = "register-driver";
    public static final String ENDPOINT_DRIVERS = "drivers";
    public static final String ENDPOINT_ADD_ROUTE = "add-route";

    public static final String ENDPOINT_ADD_HALT = "add-halt";
    public static final String ENDPOINT_ADD_ROUTE_INFO = "add-route-info";
    public static final String ENDPOINT_GET_ROUTE = "route";
    public static final String ENDPOINT_GET_ROUTE_INFO = "route-info";
    public static final String ENDPOINT_GET_HALTS = "bus-stops";
    public static final String ENDPOINT_ADD_SCHEDULE = "add-schedule";

    // bus
    public static final String ENDPOINT_ADD_BUS_DETAILS = "add-bus-details";
    public static final String ENDPOINT_BUS_INFO = "bus";
    public static final String ENDPOINT_ADD_BUS_SCHEDULE = "add-bus-schedule";
    public static final String ENDPOINT_BUS_SCHEDULE_LIST_ITEMS = "bus-schedule";

    // driver bus schedule endpoint
    public static final String ENDPOINT_DRIVER_BUS_SCHEDULE = "driver-employee/{employee-id}/bus-schedules";

    //schedule
    public static final String ENDPOINT_ADD_SCHEDULE_DETAILS = "schedules";


    // Buttons
    public static final String BUTTON_SEARCH_BUS = "Search buses";

    // location
    public static final Integer LOCATION_THRESHOLD = 50;
    public static final Integer NOTIFICATION_ID = 123;
    public static final String CHANNEL_ID = "LocationUpdateChannel";

    // request codes
    public static final Integer LOCATION_REQUEST_CODE = 1;
    public static final int REQUEST_CODE_MAP_LOCATION = 1001;

    // TextViews
    public static final String TYPO_ROUTE_INFO_INIT = "Click to add route";
    public static final String TYPO_SOURCE_INFO_INIT = "Source";
    public static final String TYPO_DESTINATION_INFO_INIT = "Destination";



    // Shared Preferences
    public static final String SHARED_PREF_DRIVER = "stsdriver";

    // EditText Views
    public static final String SOURCE = "Source";
    public static final String DESTINATION = "Destination";
}
