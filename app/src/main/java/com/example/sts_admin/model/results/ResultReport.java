package com.example.sts_admin.model.results;

import com.example.sts_admin.model.BusR;
import com.example.sts_admin.model.ScheduleR;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultReport {
//    bus, date, schedules

    @SerializedName("bus")
    private BusR busR;
    @SerializedName("date")
    private String date;
    @SerializedName("schedules")
    private List<ScheduleR> scheduleR;
    @SerializedName("end-date")
    private String apiEndDate;
    @SerializedName("start-date")
    private String apiStrartDate;


    public BusR getBusR() {
        return busR;
    }

    public void setBusR(BusR busR) {
        this.busR = busR;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ScheduleR> getScheduleR() {
        return scheduleR;
    }

    public void setScheduleR(List<ScheduleR> scheduleR) {
        this.scheduleR = scheduleR;
    }


    public String getApiEndDate() {
        return apiEndDate;
    }

    public void setApiEndDate(String apiEndDate) {
        this.apiEndDate = apiEndDate;
    }

    public String getApiStrartDate() {
        return apiStrartDate;
    }

    public void setApiStrartDate(String apiStrartDate) {
        this.apiStrartDate = apiStrartDate;
    }
}
