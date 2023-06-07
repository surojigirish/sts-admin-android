package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.PassengerUser;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPassengerDetailResponse {

    @SerializedName("id")
    private Integer id;

    @SerializedName("user")
    private List<PassengerUser> passengerUser;

//    public GetPassengerDetailResponse(Integer id, List<PassengerUser> passengerUser) {
//        this.id = id;
//        this.passengerUser = passengerUser;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<PassengerUser> getPassengerUser() {
        return passengerUser;
    }

    public void setPassengerUser(List<PassengerUser> passengerUser) {
        this.passengerUser = passengerUser;
    }
}
