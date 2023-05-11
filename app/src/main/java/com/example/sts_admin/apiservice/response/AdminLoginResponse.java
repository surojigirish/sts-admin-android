package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.Admin;

public class AdminLoginResponse {

    private int status;
    private String message;
    private Admin user;


    public AdminLoginResponse(int status, String message, Admin user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Admin getUser() {
        return user;
    }

    public void setUser(Admin user) {
        this.user = user;
    }
}
