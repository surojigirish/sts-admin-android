package com.example.sts_admin.apiservice.request;

import com.google.gson.annotations.SerializedName;

public class HaltRequest {

    @SerializedName("name")
    private String name;

    @SerializedName("longitude")
     private String longitude;
    @SerializedName("latitude")
     private String latitude;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
