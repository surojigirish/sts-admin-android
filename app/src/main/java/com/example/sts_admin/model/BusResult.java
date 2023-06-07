package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class BusResult {

    @SerializedName("capacity")
    private Integer capacity;

    @SerializedName("id")
    private Integer id;

    @SerializedName("rto-reg-no")
    private String registrationNo;

    @SerializedName("status")
    private String status;

    @SerializedName("type")
    private String type;

    public BusResult(Integer capacity, Integer id, String registrationNo, String status, String type) {
        this.capacity = capacity;
        this.id = id;
        this.registrationNo = registrationNo;
        this.status = status;
        this.type = type;
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

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
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
