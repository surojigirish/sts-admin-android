package com.example.sts_admin.apiservice.response;

import com.google.gson.annotations.SerializedName;

public class UpdateScheduleResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private Integer status;

    @SerializedName("success")
    private  boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
