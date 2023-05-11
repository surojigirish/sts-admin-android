package com.example.sts_admin.addSourceModel;


import com.example.sts_admin.model.UserAdminSource;
import com.google.gson.annotations.SerializedName;

public class SourceResponse {

    private String message;
    private int status;
    private boolean success;

    @SerializedName("user-admin")
    private UserAdminSource user_admin;


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

    public UserAdminSource getUser_admin() {
        return user_admin;
    }

    public void setUser_admin(UserAdminSource user_admin) {
        this.user_admin = user_admin;
    }
}
