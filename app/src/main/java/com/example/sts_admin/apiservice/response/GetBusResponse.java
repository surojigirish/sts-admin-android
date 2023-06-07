package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.BusResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBusResponse {

    @SerializedName("result")
    private List<BusResult> busResultList;

    @SerializedName("status")
    private Integer status;

    @SerializedName("success")
    private boolean success;

    public GetBusResponse(List<BusResult> busResultList, Integer status, boolean success) {
        this.busResultList = busResultList;
        this.status = status;
        this.success = success;
    }

    public List<BusResult> getBusResultList() {
        return busResultList;
    }

    public void setBusResultList(List<BusResult> busResultList) {
        this.busResultList = busResultList;
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
