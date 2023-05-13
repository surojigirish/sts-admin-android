package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.Bus;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BusScheduleResponse {
    @SerializedName("result")
    private List<Bus> busses;

    @SerializedName("status")
    private Integer status;

    @SerializedName("success")
    private Boolean success;

    public List<Bus> getBusses() {
        return busses;
    }

    public void setBusses(List<Bus> busses) {
        this.busses = busses;
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
