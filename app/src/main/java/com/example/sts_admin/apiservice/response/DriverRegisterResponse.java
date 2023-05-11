package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.Driver;

public class DriverRegisterResponse {

    private String message;
    private int status;
    private boolean success;

    private Driver user_driver;



    public DriverRegisterResponse(String message, int status, boolean success, Driver user_driver) {
        this.message = message;
        this.status = status;
        this.success = success;
        this.user_driver = user_driver;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Driver getUser_driver() {
        return user_driver;
    }

    public void setUser_driver(Driver user_driver) {
        this.user_driver = user_driver;
    }
}
