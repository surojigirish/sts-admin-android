package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class BusR {
//    "id": 3,
//            "reg-no": "GA-03-AL-1111",
//            "type": "LOCAL"

    @SerializedName("id")
    private Integer id;
    @SerializedName("reg-no")
    private String regNo;
    @SerializedName("type")
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
