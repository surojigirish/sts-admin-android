package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class RouteInfo {

    /*"destination-name": 1,
            "distance": "17",
            "fare": "17",
            "route-id": 4,
            "source-name": 6*/

    @SerializedName("destination-name")
    private Integer destinationId;
    @SerializedName("distance")
    private String distance;
    @SerializedName("fare")
    private String fare;
    @SerializedName("route-id")
    private Integer routeId;
    @SerializedName("source-name")
    private Integer sourceId;

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
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

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }
}
