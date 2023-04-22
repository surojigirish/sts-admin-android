package com.example.sts_admin.model;

public class User_Driver {


    private int driverId;
    private String firstname;
    private String lastname;
    private int userId;

    public User_Driver(int driverId, String firstname, String lastname, int userId) {
        this.driverId = driverId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userId = userId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
