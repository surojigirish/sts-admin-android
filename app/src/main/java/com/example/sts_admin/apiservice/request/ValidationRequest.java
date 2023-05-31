package com.example.sts_admin.apiservice.request;

import com.google.gson.annotations.SerializedName;

public class ValidationRequest {

    @SerializedName("travel-date")
    private String travelDate;
    @SerializedName("bus-schedule-id")
    private Integer busScheduleId;
    @SerializedName("booked-at")
    private String bookingTimeStamp;

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public Integer getBusScheduleId() {
        return busScheduleId;
    }

    public void setBusScheduleId(Integer busScheduleId) {
        this.busScheduleId = busScheduleId;
    }

    public String getBookingTimeStamp() {
        return bookingTimeStamp;
    }

    public void setBookingTimeStamp(String bookingTimeStamp) {
        this.bookingTimeStamp = bookingTimeStamp;
    }
}
