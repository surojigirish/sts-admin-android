package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;


public class Employee {

    @SerializedName("driver")
    private Driver driver;

    @SerializedName("id")
    private Integer id;

    @SerializedName("employee-no")
    private String employeeNumber;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("gender")
    private String gender;

    @SerializedName("lastname")
    private String lastName;

    public Employee(Driver driver, Integer id, String employeeNumber, String firstName, String gender, String lastName) {
        this.driver = driver;
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
    }

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

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


