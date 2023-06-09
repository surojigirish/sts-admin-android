package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class UserDetail {

    @SerializedName("email")
    private String email;

    @SerializedName("id")
    private Integer id;

    public UserDetail(String email, Integer id) {
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
