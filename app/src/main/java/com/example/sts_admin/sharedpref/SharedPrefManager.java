package com.example.sts_admin.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sts_admin.model.Admin;
import com.example.sts_admin.model.Bus;
import com.example.sts_admin.model.Driver;
import com.example.sts_admin.model.Schedule;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "stsadmin";
    private static final String SHARED_PREF_ADD_BUS_SCHEDULE_NAME = "stsbusschedule";
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

    public void saveDriver(Integer empId, String employeeNo,String firstnaame,String lastname) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ADD_BUS_SCHEDULE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("empId", empId);
        editor.putString("empNO", employeeNo);
        editor.putString("fname", firstnaame);
        editor.putString("lname", lastname);
        editor.apply();
    }

    public Driver getDriverDetails() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ADD_BUS_SCHEDULE_NAME, Context.MODE_PRIVATE);
        return new Driver(sharedPreferences.getInt("empId", 0),
                sharedPreferences.getString("empNo", ""),
                sharedPreferences.getString("fname", ""),
                sharedPreferences.getString("lname", ""));
    }

    public void clearScheduleData() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ADD_BUS_SCHEDULE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
