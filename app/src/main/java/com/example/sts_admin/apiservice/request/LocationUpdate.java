package com.example.sts_admin.apiservice.request;

import com.google.gson.annotations.SerializedName;

public class LocationUpdate {

    @SerializedName("latitude")
    private Double lat;
    @SerializedName("longitude")
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
