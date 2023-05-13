package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class Routes {

    @SerializedName("destination")
    private String destination;

    @SerializedName("route-id")
    private Integer routeId;

    @SerializedName("source")
    private String source;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
