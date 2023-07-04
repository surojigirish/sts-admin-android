package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class RouteR {
    @SerializedName("destination") private Destination destination;
    @SerializedName("source") private Source source;
    @SerializedName("id") private Integer id;

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
