package com.example.sts_admin.model;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

public class Halts {
    /*
    *
    * "lat": "73.9316271852971",
        "long": "15.348151557147384",
        "source-id": 8,
        "source-name": "VEREM"*/

    @SerializedName("lat")
    private String latitude;
    @SerializedName("long")
    private String longitude;
    @SerializedName("source-id")
    private Integer sourceId;
    @SerializedName("source-name")
    private String name;

    // temp
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String haltName;

    public Halts(Integer id, String haltName, String latitude, String longitude) {
        this.id = id;
        this.haltName = haltName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Halts() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHaltName() {
        return haltName;
    }

    public void setHaltName(String haltName) {
        this.haltName = haltName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
