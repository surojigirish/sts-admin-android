package com.example.sts_admin.apiservice.request;

import com.google.gson.annotations.SerializedName;

public class UpdateBusDetailsRequest {


    @SerializedName("status")
    private String status;

    @SerializedName("type")
    private String type;

//    public UpdateBusDetailsRequest(String status) {
//        this.status = status;
//    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
