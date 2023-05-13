package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class Bus {

    @SerializedName("capacity")
    private Integer capacity;
    @SerializedName("id")
    private Integer id;
    @SerializedName("rto-reg-no")
    private String regNo;
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;

    public Bus(Integer id, String regNo) {
        this.id = id;
        this.regNo = regNo;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
