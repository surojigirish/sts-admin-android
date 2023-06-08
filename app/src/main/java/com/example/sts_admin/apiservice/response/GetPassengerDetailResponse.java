package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.PassengerUser;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPassengerDetailResponse {

    @SerializedName("id")
    private Integer id;

    @SerializedName("user")
    private PassengerUser passengerUser;

    public GetPassengerDetailResponse(Integer id, PassengerUser passengerUser) {
        this.id = id;
        this.passengerUser = passengerUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PassengerUser getPassengerUser() {
        return passengerUser;
    }

    public void setPassengerUser(PassengerUser passengerUser) {
        this.passengerUser = passengerUser;
    }
}
