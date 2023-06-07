package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class AddRouteDetails {

    @SerializedName("destination")
    private Destination destination;

    @SerializedName("id")
    private  Integer id;

    @SerializedName("source")
    private RouteSource routeSource;


    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RouteSource getRouteSource() {
        return routeSource;
    }

    public void setRouteSource(RouteSource routeSource) {
        this.routeSource = routeSource;
    }
}
