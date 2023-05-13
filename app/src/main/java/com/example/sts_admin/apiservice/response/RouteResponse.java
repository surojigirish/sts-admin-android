package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.Routes;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteResponse {
    @SerializedName("result")
    List<Routes> routesList;

    @SerializedName("status")
    private  String status;

    @SerializedName("success")
    private  boolean success;

    public List<Routes> getRoutesList() {
        return routesList;
    }

    public void setRoutesList(List<Routes> routesList) {
        this.routesList = routesList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
