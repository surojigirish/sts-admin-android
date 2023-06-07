package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class RouteSource {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
