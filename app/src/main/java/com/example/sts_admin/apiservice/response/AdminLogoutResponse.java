package com.example.sts_admin.apiservice.response;

public class AdminLogoutResponse {
    private int status;
    private String message;
    private boolean success;

    public AdminLogoutResponse(int status, String message, boolean success) {
        this.status = status;
        this.message = message;
        this.success = success;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
