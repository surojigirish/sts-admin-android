package com.example.sts_admin.apiservice.request;

import com.google.gson.annotations.SerializedName;

public class AddBusRequest {

//    "register-no":"GA-08-T-0203",
//    "capacity":"25",
//    "type": "LOCAL",
//    "status": "ACTIVE"

    private @SerializedName("register-no") String RegNo;
    private @SerializedName("capacity") String capacity;
    private @SerializedName("type") String type;
    private @SerializedName("status") String status;

    public String getRegNo() {
        return RegNo;
    }

    public void setRegNo(String regNo) {
        RegNo = regNo;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
