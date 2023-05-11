package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class UserAdmin {

    private  String destination;
    @SerializedName("route-id")
    private  int route_id;
    private String source;

    public UserAdmin(String destination, int route_id, String source) {
        this.destination = destination;
        this.route_id = route_id;
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
