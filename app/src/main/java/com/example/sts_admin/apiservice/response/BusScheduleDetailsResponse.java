package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.Result;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BusScheduleDetailsResponse {

    @SerializedName("result")
    List<Result> result;

    @SerializedName("status")
    private Integer status;

    @SerializedName("success")
    private String success;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
