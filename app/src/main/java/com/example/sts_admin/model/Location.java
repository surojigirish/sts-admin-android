package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class Location {

    // API serialized variables for Location model class

    @SerializedName("id")
    private Integer id;
    @SerializedName("lat")
    private Double latitude;
    @SerializedName("lng")
    private Double longitude;
    @SerializedName("updated-at")
    private String updatedAtDate;

    // Getters and setters for variables init

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getUpdatedAtDate() {
        return updatedAtDate;
    }

    public void setUpdatedAtDate(String updatedAtDate) {
        this.updatedAtDate = updatedAtDate;
    }
}
