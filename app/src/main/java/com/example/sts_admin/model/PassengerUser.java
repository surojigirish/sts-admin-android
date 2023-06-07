package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class PassengerUser {

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("photo")
    private String photo;

    @SerializedName("userId")
    private Integer userId;
//
//    public PassengerUser(String firstname, String lastname, String photo, Integer userId) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.photo = photo;
//        this.userId = userId;
//    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
