package com.example.sts_admin.apiservice.request;

import com.google.gson.annotations.SerializedName;

public class RouteInfoRequest {

    @SerializedName("route-id")
    private Integer routeId;
    @SerializedName("source-id")
    private Integer sourceId;
    @SerializedName("destination-id")
    private Integer destinationId;
    @SerializedName("distance")
    private String distance;
    @SerializedName("fare")
    private String fare;

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
}
