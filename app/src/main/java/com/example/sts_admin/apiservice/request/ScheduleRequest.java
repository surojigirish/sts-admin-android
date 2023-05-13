package com.example.sts_admin.apiservice.request;

import com.google.gson.annotations.SerializedName;

public class ScheduleRequest {

    @SerializedName("departure-at")
    private String departureTime;

      @SerializedName("arrival-at")
    private String arrivalTime;

      @SerializedName("duration")
    private String duration;

      @SerializedName("route-id")
    private Integer routeId;

//    public ScheduleRequest(String departureTime, String arrivalTime, String duration, String routeId) {
//        this.departureTime = departureTime;
//        this.arrivalTime = arrivalTime;
//        this.duration = duration;
//        this.routeId = routeId;
//    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }
}
