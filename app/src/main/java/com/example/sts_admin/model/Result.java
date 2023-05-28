package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("available-seats")
    private String availableSeats;

    @SerializedName("bus-id")
    private Integer busId;

    @SerializedName("date")
    private String date;

    @SerializedName("schedule-id")
    private Integer scheduleId;

    public String getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(String availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }
}
