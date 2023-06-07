package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class RouteInfo {

    @SerializedName("destination")
    private Halts destination;
    @SerializedName("distance")
    private String distance;

    @SerializedName("type")
    private String Type;
    @SerializedName("fare")
    private String fare;
    @SerializedName("route-id")
    private Integer routeId;
    @SerializedName("source")
    private Halts source;

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public Halts getDestination() {
        return destination;
    }

    public void setDestination(Halts destination) {
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

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Halts getSource() {
        return source;
    }

    public void setSource(Halts source) {
        this.source = source;
    }
}
