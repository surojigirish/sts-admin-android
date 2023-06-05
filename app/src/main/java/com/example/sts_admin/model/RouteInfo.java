package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class RouteInfo {

    @SerializedName("destination")
    private Halts destinationId;
    @SerializedName("distance")
    private String distance;

    @SerializedName("type")
    private String Type;
    @SerializedName("fare")
    private String fare;
    @SerializedName("route-id")
    private Integer routeId;
    @SerializedName("source")
    private Halts sourceId;

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public Halts getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Halts destinationId) {
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

    public Halts getSourceId() {
        return sourceId;
    }

    public void setSourceId(Halts sourceId) {
        this.sourceId = sourceId;
    }
}
