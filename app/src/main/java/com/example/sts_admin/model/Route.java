package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class Route {

    @SerializedName("destination")
    private  String destination;
    @SerializedName("route-id")
    private  int routeId;
    @SerializedName("source")
    private String source;

    public Route(String destination, int routeId, String source) {
        this.destination = destination;
        this.routeId = routeId;
        this.source = source;
    }

    public Route () {
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
