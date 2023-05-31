package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;


public class Employee {

    @SerializedName("driver")
    private Driver driver;

    @SerializedName("id")
    private Integer id;

//    public Employee(Driver driver, Integer id) {
//        this.driver = driver;
//        this.id = id;
//    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


