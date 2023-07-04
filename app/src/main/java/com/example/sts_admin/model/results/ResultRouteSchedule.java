package com.example.sts_admin.model.results;

import com.example.sts_admin.model.Destination;
import com.example.sts_admin.model.Source;
import com.google.gson.annotations.SerializedName;

public class ResultRouteSchedule {
    @SerializedName("arrival-at")
    String arrivalAt;
    @SerializedName("departure-at")
    String departureAt;
    @SerializedName("duration")
    String duration;
    @SerializedName("id")
    String id;
    @SerializedName("destination")
    Destination destination;
    @SerializedName("source")
    Source source;

    @SerializedName("status")
    Integer status;
    @SerializedName("succeed")
    Boolean success;

    public ResultRouteSchedule(String arrivalAt, String departureAt, String duration, String id, Destination destination, Source source) {
        this.arrivalAt = arrivalAt;
        this.departureAt = departureAt;
        this.duration = duration;
        this.id = id;
        this.destination = destination;
        this.source = source;
    }

    public String getArrivalAt() {
        return arrivalAt;
    }

    public void setArrivalAt(String arrivalAt) {
        this.arrivalAt = arrivalAt;
    }

    public String getDepartureAt() {
        return departureAt;
    }

    public void setDepartureAt(String departureAt) {
        this.departureAt = departureAt;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
