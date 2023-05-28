package com.example.sts_admin.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sts_admin.apiservice.request.ScheduleRequest;
import com.example.sts_admin.model.Admin;
import com.example.sts_admin.model.Bus;
import com.example.sts_admin.model.Driver;
import com.example.sts_admin.model.Route;
import com.example.sts_admin.model.Routes;
import com.example.sts_admin.model.Schedule;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "stsadmin";
    private static final String SHARED_PREF_ADD_BUS_SCHEDULE_NAME = "stsbusschedule";
    private static final String SHARED_PREF_DRIVER_DETAILS = "stsdriverdetails";
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

    public boolean isLogged() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
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
        editor.putString("source", source);
        editor.putString("destination", destination);
        editor.apply();
    }

    public Bus getBusDetails() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ADD_BUS_SCHEDULE_NAME, Context.MODE_PRIVATE);
        return new Bus(sharedPreferences.getInt("busId", 0),
                sharedPreferences.getString("busRegNo", ""));
    }

    public Schedule getScheduleDetails() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ADD_BUS_SCHEDULE_NAME, Context.MODE_PRIVATE);
        return new Schedule(sharedPreferences.getInt("scheduleId", 0),
                sharedPreferences.getString("source", ""),
                sharedPreferences.getString("destination", ""));
    }


    public void saveDriver(Integer driverId, String driverFirstName,String driverLastName) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_DRIVER_DETAILS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("driverFirstName", driverFirstName);
        editor.putString("driverLastName", driverLastName);
        editor.putInt("driverId", driverId);
        editor.apply();
    }
    public Driver getDriverDetails() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_DRIVER_DETAILS, Context.MODE_PRIVATE);
        return new Driver(sharedPreferences.getString("driverFirstName",""),
                sharedPreferences.getString("driverLastName",""),
                sharedPreferences.getInt("driverId",0));
    }

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


