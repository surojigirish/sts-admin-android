package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class Schedule {

//    "arrival-at": "18:00",
//        "departure-at": "17:15",
//        "duration": "45",
//        "id": 1,
//        "route-destination": "PANJIM",
//        "route-source": "MARGAO"


    @SerializedName("arrival-at")
    private String arrivalAt;
    @SerializedName("departure-at")
    private String departureAt;
    @SerializedName("duration")
    private String duration;
    @SerializedName("route-destination")
    private String routeDestination;
    @SerializedName("route-source")
    private String routeSource;
    @SerializedName("id")
    private Integer id;

    public Schedule(Integer id, String routeSource, String routeDestination) {
        this.routeDestination = routeDestination;
        this.routeSource = routeSource;
        this.id = id;
    }

    public String getArrivalAt() {
        return arrivalAt;
    }

    public void setArrivalAt(String arrivalAt) {
        this.arrivalAt = arrivalAt;
    }

    public String getDepartureAt() {
        return departureAt;
    }

    public void setDepartureAt(String departureAt) {
        this.departureAt = departureAt;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRouteDestination() {
        return routeDestination;
    }

    public void setRouteDestination(String routeDestination) {
        this.routeDestination = routeDestination;
    }

    public String getRouteSource() {
        return routeSource;
    }

    public void setRouteSource(String routeSource) {
        this.routeSource = routeSource;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
