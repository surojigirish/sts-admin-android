package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Passengers {

    @SerializedName("passenger")
    private PassengerDetail passengerDetail;

    @SerializedName("user")
    private UserDetail userDetails;

    public Passengers(PassengerDetail passengerDetail, UserDetail userDetails) {
        this.passengerDetail = passengerDetail;
        this.userDetails = userDetails;
    }

    public PassengerDetail getPassengerDetail() {
        return passengerDetail;
    }

    public void setPassengerDetail(PassengerDetail passengerDetail) {
        this.passengerDetail = passengerDetail;
    }

    public UserDetail getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetail userDetails) {
        this.userDetails = userDetails;
    }
}
