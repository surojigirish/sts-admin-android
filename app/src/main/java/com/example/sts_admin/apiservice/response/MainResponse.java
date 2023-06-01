package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.results.ListOfBusSchedule;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainResponse {

    @SerializedName("status")
    private Integer statusCode;
    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private String message;

    // Different sets of results
    @SerializedName("results")
    private List<ListOfBusSchedule> listOfBusSchedule;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
