package com.example.sts_admin.driverRegistrationModel;

import com.example.sts_admin.model.User;
import com.example.sts_admin.model.UserDriver;

public class RegisterResponse {

    private String message;
    private int status;
    private boolean success;

    private UserDriver user_driver;



    public RegisterResponse(String message, int status, boolean success, UserDriver user_driver) {
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

    public UserDriver getUser_driver() {
        return user_driver;
    }

    public void setUser_driver(UserDriver user_driver) {
        this.user_driver = user_driver;
    }
}
