package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.results.ListOfBusSchedule;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainResponse {

    @SerializedName("results")
    private List<ListOfBusSchedule> listOfBusSchedule;
    @SerializedName("status")
    private Integer statusCode;
    @SerializedName("success")
    private Boolean success;

    public List<ListOfBusSchedule> getListOfBusSchedule() {
        return listOfBusSchedule;
    }

    public void setListOfBusSchedule(List<ListOfBusSchedule> listOfBusSchedule) {
        this.listOfBusSchedule = listOfBusSchedule;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
