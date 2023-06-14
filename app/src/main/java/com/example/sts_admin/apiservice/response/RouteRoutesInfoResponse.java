package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.Route;
import com.example.sts_admin.model.RouteInfoResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteRoutesInfoResponse {

    @SerializedName("status")
    private int status;
    @SerializedName("success")
    private boolean success;
    @SerializedName("route")
    private Route route;
    @SerializedName("routes-info")
    private List<RouteInfoResult> routeInfoResults;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<RouteInfoResult> getRouteInfoResults() {
        return routeInfoResults;
    }

    public void setRouteInfoResults(List<RouteInfoResult> routeInfoResults) {
        this.routeInfoResults = routeInfoResults;
    }
}
