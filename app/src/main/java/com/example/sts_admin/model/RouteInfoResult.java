package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

import javax.xml.transform.Source;

public class RouteInfoResult {

    @SerializedName("destination")
    private Destination destination;

    @SerializedName("distance")
    private String distance;

    @SerializedName("fare")
    private String fare;

    @SerializedName("id")
    private Integer id;

    @SerializedName("route-id")
    private Integer routeId;

    @SerializedName("source")
    private RouteSource routeSource;

    public RouteInfoResult(Destination destination, String distance, String fare, Integer id, Integer routeId, RouteSource routeSource) {
        this.destination = destination;
        this.distance = distance;
        this.fare = fare;
        this.id = id;
        this.routeId = routeId;
        this.routeSource = routeSource;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public RouteSource getRouteSource() {
        return routeSource;
    }

    public void setRouteSource(RouteSource routeSource) {
        this.routeSource = routeSource;
    }
}
