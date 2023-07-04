package com.example.sts_admin.model.results;

import com.example.sts_admin.model.BusR;
import com.example.sts_admin.model.ScheduleR;
import com.google.gson.annotations.SerializedName;

public class ResultReport {
//    bus, date, schedules

    @SerializedName("bus")
    private BusR busR;
    @SerializedName("date")
    private String date;
    @SerializedName("schedules")
    private ScheduleR scheduleR;

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

    public ScheduleR getScheduleR() {
        return scheduleR;
    }

    public void setScheduleR(ScheduleR scheduleR) {
        this.scheduleR = scheduleR;
    }
}
