package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class Passenger {
//    "passenger": {
//                "category": "ADULT",
//                "first_name": "Allister",
//                "gender": "MALE",
//                "id": 8,
//                "last_name": "lopes"
//            },


    @SerializedName("category")
    private String category;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("id")
    private Integer passengerId;
    @SerializedName("last_name")
    private String lastName;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
