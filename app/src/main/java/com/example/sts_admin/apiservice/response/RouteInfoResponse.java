package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.RouteInfo;
import com.google.gson.annotations.SerializedName;

public class RouteInfoResponse {
    /*
    * {
    "message": "route info added successfully",
    "result": {
        "destination-name": 1,
        "distance": "17",
        "fare": "17",
        "route-id": 4,
        "source-name": 6
    },
    "status": 200,
    "success": true
}
    * */

    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private RouteInfo result;
    @SerializedName("status")
    private Integer status;
    @SerializedName("success")
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RouteInfo getResult() {
        return result;
    }

    public void setResult(RouteInfo result) {
        this.result = result;
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
