package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;


public class Employee {

    @SerializedName("driver")
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
