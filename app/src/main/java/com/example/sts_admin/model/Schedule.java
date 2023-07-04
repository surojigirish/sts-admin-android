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
    @SerializedName("destination")
    private Destination routeDestination;
    @SerializedName("source")
    private Source routeSource;
    @SerializedName("id")
    private Integer id;

    @SerializedName("route")
    private RouteModel route;


    public Schedule(Integer id, RouteModel route) {
        this.id = id;
        this.route = route;
    }

    public RouteModel getRoute() {
        return route;
    }

    public void setRoute(RouteModel route) {
        this.route = route;
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

    public Destination getRouteDestination() {
        return routeDestination;
    }

    public void setRouteDestination(Destination routeDestination) {
        this.routeDestination = routeDestination;
    }

    public Source getRouteSource() {
        return routeSource;
    }

    public void setRouteSource(Source routeSource) {
        this.routeSource = routeSource;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;

    }




    /* Schedule Model for Pass Schedule Info
    * model ID is already having serialized format and variable
    * and also duration so need not make a variable for them 2
    *
    *
    *  */


    @SerializedName("arrival")
    private String arrivalTime;

    @SerializedName("arrival-stand")
    private String arrivalBusStand;

    @SerializedName("departure")
    private String departureTime;

    @SerializedName("departure-stand")
    private String departureBusStand;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalBusStand() {
        return arrivalBusStand;
    }

    public void setArrivalBusStand(String arrivalBusStand) {
        this.arrivalBusStand = arrivalBusStand;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureBusStand() {
        return departureBusStand;
    }

    public void setDepartureBusStand(String departureBusStand) {
        this.departureBusStand = departureBusStand;
    }
}
