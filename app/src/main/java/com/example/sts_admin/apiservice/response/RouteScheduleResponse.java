package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.results.ResultRouteSchedule;
import com.google.gson.annotations.SerializedName;

public class RouteScheduleResponse {
    @SerializedName("result")
    ResultRouteSchedule resultRouteSchedule;

    public ResultRouteSchedule getResultRouteSchedule() {
        return resultRouteSchedule;
    }

    public void setResultRouteSchedule(ResultRouteSchedule resultRouteSchedule) {
        this.resultRouteSchedule = resultRouteSchedule;
    }
}
