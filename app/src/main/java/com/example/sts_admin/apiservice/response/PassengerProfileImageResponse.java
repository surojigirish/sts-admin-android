package com.example.sts_admin.apiservice.response;

import com.google.gson.annotations.SerializedName;

public class PassengerProfileImageResponse {
//    "message": "Image not found",
//    "status": 404,
//    "success": false

    @SerializedName("message") String message;
    @SerializedName("status") Integer status;
    @SerializedName("success") Boolean success;

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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
