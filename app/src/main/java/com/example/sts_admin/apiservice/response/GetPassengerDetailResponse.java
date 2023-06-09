package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.Passengers;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPassengerDetailResponse {

  @SerializedName("passengers")
    private List<Passengers> passengers;

  @SerializedName("status")
    private Integer status;

  @SerializedName("success")
    private boolean success;

    public GetPassengerDetailResponse(List<Passengers> passengers, Integer status, boolean success) {
        this.passengers = passengers;
        this.status = status;
        this.success = success;
    }

    public List<Passengers> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passengers> passengers) {
        this.passengers = passengers;
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
