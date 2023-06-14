package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class Route {

    @SerializedName("destination")
    private  String destination;
    @SerializedName("route-id")
    private  int routeId;
    @SerializedName("id")
    private int id;
    @SerializedName("source")
    private String source;

    @SerializedName("type")
    private String type;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
