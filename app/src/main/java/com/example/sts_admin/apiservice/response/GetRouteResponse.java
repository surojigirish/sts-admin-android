package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.AddRouteDetails;
import com.example.sts_admin.model.Route;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRouteResponse {

    @SerializedName("routes")
    private List<AddRouteDetails> addRouteDetailsList;

    @SerializedName("status")
    private Integer status;

    @SerializedName("success")
    private boolean success;

    public List<AddRouteDetails> getAddRouteDetailsList() {
        return addRouteDetailsList;
    }

    public void setAddRouteDetailsList(List<AddRouteDetails> addRouteDetailsList) {
        this.addRouteDetailsList = addRouteDetailsList;
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
