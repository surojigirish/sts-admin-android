package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class RouteModel {

    @SerializedName("destination")
    private Halts destination;
    @SerializedName("source")
    private Halts source;
    @SerializedName("id")
    private int id;

    public Halts getDestination() {
        return destination;
    }

    public void setDestination(Halts destination) {
        this.destination = destination;
    }

    public Halts getSource() {
        return source;
    }

    public void setSource(Halts source) {
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
