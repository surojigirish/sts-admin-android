package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Session {

    @SerializedName("employee")
    private Employee employee;

    @SerializedName("token")
    private String token;

    @SerializedName("user")
    private User user;

    public Session(Employee employee, String token, User user) {
        this.employee = employee;
        this.token = token;
        this.user = user;
    }


    public Session(String licenseNo, String employeeNo, String firstname, String gender, int id, String lastname, String string, String email, int userId) {
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

