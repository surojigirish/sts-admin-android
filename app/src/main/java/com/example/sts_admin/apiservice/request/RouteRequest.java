package com.example.sts_admin.apiservice.request;

import com.google.gson.annotations.SerializedName;

public class RouteRequest {

    @SerializedName("source-id")
    private int sourceId;
    @SerializedName("destination-id")
    private int destinationId;

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }
}
