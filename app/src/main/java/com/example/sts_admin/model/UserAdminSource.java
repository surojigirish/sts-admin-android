package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class UserAdminSource {



      private double lat;

      @SerializedName("long")
      private double longitude;

      @SerializedName("source-id")
      private int source_id;

      @SerializedName("source-name")
      private String source_name;

    public UserAdminSource(double lat, double longitude, int source_id, String source_name) {
        this.lat = lat;
        this.longitude = longitude;
        this.source_id = source_id;
        this.source_name = source_name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }
}
