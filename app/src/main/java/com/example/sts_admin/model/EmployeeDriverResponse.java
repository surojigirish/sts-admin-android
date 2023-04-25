package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeDriverResponse {

    @SerializedName("status")
    private Integer status;
    @SerializedName("employee")
    private List<UserDriver> employee;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<UserDriver> getEmployee() {
        return employee;
    }

    public void setEmployee(List<UserDriver> employee) {
        this.employee = employee;
    }
}
