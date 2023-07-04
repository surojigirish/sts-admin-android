package com.example.sts_admin.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sts_admin.Consts;
import com.example.sts_admin.model.Admin;
import com.example.sts_admin.model.Bus;
import com.example.sts_admin.model.Driver;
import com.example.sts_admin.model.Employee;
import com.example.sts_admin.model.Halts;
import com.example.sts_admin.model.RouteModel;
import com.example.sts_admin.model.Schedule;
import com.example.sts_admin.model.Session;
import com.example.sts_admin.model.User;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "stsadmin";
    private static final String SHARED_PREF_ADD_BUS_SCHEDULE_NAME = "stsbusschedule";
    private static final String SHARED_PREF_ROUTE = "stsroute";
    private static final String SHARED_PREF_DRIVER_DETAILS = "stsdriverlogin";
    SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(Admin user) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("id", user.getUserId());
        editor.putString("email", user.getEmail());
        editor.putString("firstname", user.getFirstname());
        editor.putString("lastname", user.getLastname());
        editor.putString("token", user.getToken());
        editor.putBoolean("logged", true);
        editor.apply();
    }

    public boolean isAdminLogged() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged", false);
    }

    public boolean isDriverLogged() {
        sharedPreferences = context.getSharedPreferences(Consts.SHARED_PREF_DRIVER, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged", false);
    }

    public Admin getUser() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Admin(sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("firstname", null),
                sharedPreferences.getString("lastname", null),
                sharedPreferences.getString("token", null));
    }

    public void logout() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void driverLogout() {
        sharedPreferences = context.getSharedPreferences(Consts.SHARED_PREF_DRIVER, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void saveBus(Integer busId, String busRegNo) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ADD_BUS_SCHEDULE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("busId", busId);
        editor.putString("busRegNo", busRegNo);
        editor.apply();
    }

    public void saveSchedule(Integer scheduleId, String source, String destination) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ADD_BUS_SCHEDULE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("scheduleId", scheduleId);
        editor.putString("sName", source);
        editor.putString("dName", destination);
        editor.apply();
    }

    public void saveRoute(Integer routeId) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ROUTE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("routeId", routeId);
        editor.apply();
    }

    public Bus getBusDetails() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ADD_BUS_SCHEDULE_NAME, Context.MODE_PRIVATE);
        return new Bus(sharedPreferences.getInt("busId", 0),
                sharedPreferences.getString("busRegNo", ""));
    }

//<<<<<<< HEAD
//    public Schedule getScheduleDetails() {
//        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ADD_BUS_SCHEDULE_NAME, Context.MODE_PRIVATE);
//        return new Schedule(sharedPreferences.getInt("scheduleId", 0),
//                sharedPreferences.getString("source", ""),
//                sharedPreferences.getString("destination", ""));
//    }
//=======
    public Schedule getScheduleDetails() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ADD_BUS_SCHEDULE_NAME, Context.MODE_PRIVATE);

        // Source
        int sId = sharedPreferences.getInt("sId", -1);
        String sName = sharedPreferences.getString("sName", "");
        String sLat = sharedPreferences.getString("sLat", "");
        String sLng = sharedPreferences.getString("sLng", "");
        Halts sourceHalt = new Halts(sId, sName, sLat, sLng);

        // Destination
        int dId = sharedPreferences.getInt("dId", -1);
        String dName = sharedPreferences.getString("dName", "");
        String dLat = sharedPreferences.getString("dLat", "");
        String dLng = sharedPreferences.getString("dLng", "");
        Halts destinationHalt = new Halts(dId, dName, dLat, dLng);

        // Route
        int routeId = sharedPreferences.getInt("routeId", -1);
        RouteModel route = new RouteModel(destinationHalt, sourceHalt, routeId);

        // Schedule
        int scheduleId = sharedPreferences.getInt("scheduleId", -1);

        return new Schedule(scheduleId, route);
    }


//>>>>>>> 0b6d6d98c1a0ca707dbb7c5ebce63d2f19a0af09
    public void saveDriver(Session driver){
        sharedPreferences = context.getSharedPreferences(Consts.SHARED_PREF_DRIVER, Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();

        editor.putString("licenseNo",driver.getEmployee().getDriver().getLicenseNo());
        editor.putString("employeeNo",driver.getEmployee().getEmployeeNumber());
        editor.putString("firstName",driver.getEmployee().getFirstName());
        editor.putString("gender",driver.getEmployee().getGender());
        editor.putInt("empId",driver.getEmployee().getId());
        editor.putString("lastname",driver.getEmployee().getLastName());
        editor.putString("token",driver.getToken());
        editor.putString("email",driver.getUser().getEmail());
        editor.putInt("userId",driver.getUser().getId());
        editor.putBoolean("logged",true);
        editor.apply();
    }

    public Session getDriverSession(){
        sharedPreferences = context.getSharedPreferences(Consts.SHARED_PREF_DRIVER, Context.MODE_PRIVATE);

        // User
        int userId = sharedPreferences.getInt("userId", -1);
        String email = sharedPreferences.getString("email", "");

        // Session
        String token = sharedPreferences.getString("token", "");

        // Employee
        int employeeId = sharedPreferences.getInt("empId", -1);
        String firstName = sharedPreferences.getString("firstname", "");
        String lastName = sharedPreferences.getString("lastname", "");
        String gender = sharedPreferences.getString("gender", "");
        String employeeNo = sharedPreferences.getString("employeeNo", "");

        // Driver
        String licenseNumber = sharedPreferences.getString("licenseNo", "");

        // Create a new user instance
        User user = new User(userId, email);
        // Create a new driver instance
        Driver driver = new Driver(licenseNumber);
        // Create a new employee instance
        Employee employee = new Employee(driver, employeeId, employeeNo, firstName, gender, lastName);

        // Return new instance of session
        return new Session(employee, token, user);
    }


//    public void saveDriver(Integer driverId, String driverFirstName,String driverLastName) {
//        sharedPreferences = context.getSharedPreferences(SHARED_PREF_DRIVER_DETAILS, Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//        editor.putString("driverFirstName", driverFirstName);
//        editor.putString("driverLastName", driverLastName);
//        editor.putInt("driverId", driverId);
//        editor.apply();
//    }
//    public Driver getDriverDetails() {
//        sharedPreferences = context.getSharedPreferences(SHARED_PREF_DRIVER_DETAILS, Context.MODE_PRIVATE);
//        return new Driver(sharedPreferences.getString("driverFirstName",""),
//                sharedPreferences.getString("driverLastName",""),
//                sharedPreferences.getInt("driverId",0));
//    }

//    public void saveRoute(String departureAt, String arrivalAt) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_ADD_BUS_SCHEDULE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("departure", departureAt);
//        editor.putString("arrival", arrivalAt);
//        editor.apply();
}

//    public Schedule getSchedule() {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_ADD_BUS_SCHEDULE_NAME, Context.MODE_PRIVATE);
//        return new Schedule(
//                sharedPreferences.getString("departure", ""),
//                sharedPreferences.getString("source", ""));
//    }


