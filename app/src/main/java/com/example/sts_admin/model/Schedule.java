package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class Schedule {
//    "arrival-time": "20:00",
//                "departure-time": "17:00",
//                "duration": "45",
//                "schedule-id": 8



    @SerializedName( "arrival-time")
    private String arrivalTime;

    @SerializedName("departure-time")
    private String departureTime;

    @SerializedName("duration")
    private String duration;

    @SerializedName("schedule-id")
    private String scheduleId;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
}
