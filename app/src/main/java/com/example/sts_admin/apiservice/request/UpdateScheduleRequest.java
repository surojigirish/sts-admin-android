package com.example.sts_admin.apiservice.request;

import com.google.gson.annotations.SerializedName;

public class UpdateScheduleRequest {

    @SerializedName("departure_at")
    String update_departure_at;
    @SerializedName("arrival_at")
    String update_arrival_at;
    @SerializedName("duration")
    String update_duration;

    public String getUpdate_departure_at() {
        return update_departure_at;
    }

    public void setUpdate_departure_at(String update_departure_at) {
        this.update_departure_at = update_departure_at;
    }

    public String getUpdate_arrival_at() {
        return update_arrival_at;
    }

    public void setUpdate_arrival_at(String update_arrival_at) {
        this.update_arrival_at = update_arrival_at;
    }

    public String getUpdate_duration() {
        return update_duration;
    }

    public void setUpdate_duration(String update_duration) {
        this.update_duration = update_duration;
    }
}
