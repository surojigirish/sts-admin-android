package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.Driver;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeDriverResponse {

    @SerializedName("status")
    private Integer status;
    @SerializedName("employee")
    private List<Driver> employee;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Driver> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Driver> employee) {
        this.employee = employee;
    }
}
