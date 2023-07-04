package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class Source {
    @SerializedName("id") String id;
    @SerializedName("name") String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
