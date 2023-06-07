package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.RouteInfoResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRouteInfoResponse {

    @SerializedName("result")
    private List<RouteInfoResult>routeInfoResultList;

    @SerializedName("status")
    private Integer status;

    @SerializedName("success")
    private boolean success;

    public GetRouteInfoResponse(List<RouteInfoResult> routeInfoResultList, Integer status, boolean success) {
        this.routeInfoResultList = routeInfoResultList;
        this.status = status;
        this.success = success;
    }

    public List<RouteInfoResult> getRouteInfoResultList() {
        return routeInfoResultList;
    }

    public void setRouteInfoResultList(List<RouteInfoResult> routeInfoResultList) {
        this.routeInfoResultList = routeInfoResultList;
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
