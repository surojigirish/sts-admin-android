package com.example.sts_admin.loginModel;

public class LoginResponse {

    private int status;
    private String message;
    private AdminLoginResponse user;


    public LoginResponse(int status, String message, AdminLoginResponse user) {
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

    public AdminLoginResponse getUser() {
        return user;
    }

    public void setUser(AdminLoginResponse user) {
        this.user = user;
    }
}
