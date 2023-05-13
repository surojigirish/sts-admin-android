package com.example.sts_admin.apiservice.response;

import com.google.gson.annotations.SerializedName;

public class AddBusResponse {

//     "message": "bus registered successfully",
//    "status": 200,
//    "success": true

    private @SerializedName("message") String message;
    private @SerializedName("status") Integer status;
    private @SerializedName("success") boolean success;


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
