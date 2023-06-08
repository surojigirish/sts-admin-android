package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Passengers {

   @SerializedName("passenger")
    private List<PassengerDetail> passengerDetail;

   @SerializedName("user")
    private List<UserDetail> userDetails;

    public Passengers(List<PassengerDetail> passengerDetail, List<UserDetail> userDetails) {
        this.passengerDetail = passengerDetail;
        this.userDetails = userDetails;
    }

    public List<PassengerDetail> getPassengerDetail() {
        return passengerDetail;
    }

    public void setPassengerDetail(List<PassengerDetail> passengerDetail) {
        this.passengerDetail = passengerDetail;
    }

    public List<UserDetail> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetail> userDetails) {
        this.userDetails = userDetails;
    }
}
