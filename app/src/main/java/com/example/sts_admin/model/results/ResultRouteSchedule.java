package com.example.sts_admin.model.results;

import com.example.sts_admin.model.Destination;
import com.example.sts_admin.model.Source;
import com.google.gson.annotations.SerializedName;

public class ResultRouteSchedule {

    @SerializedName("arrival-at")
    private String arrivalAt;
    @SerializedName("departure-at")
    private String departureAt;
    @SerializedName("duration")
    private String duration;
    @SerializedName("id")
    private String id;
    @SerializedName("destination")
    private Destination destination;
    @SerializedName("source")
    private Source source;


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
}
