package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.results.ResultRouteSchedule;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteScheduleResponse {
    @SerializedName("result")
    List<ResultRouteSchedule> resultRouteSchedule;
    @SerializedName("status") Integer status;
    @SerializedName("succeed") Boolean succeed;

    public List<ResultRouteSchedule> getResultRouteSchedule() {
        return resultRouteSchedule;
    }

    public void setResultRouteSchedule(List<ResultRouteSchedule> resultRouteSchedule) {
        this.resultRouteSchedule = resultRouteSchedule;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }
}
