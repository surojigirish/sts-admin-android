package com.example.sts_admin.apiservice.request;

import com.google.gson.annotations.SerializedName;

public class AddBusScheduleRequest {
//    {
//    "bus-id": 2,
//    "schedule-id": 7,
//    "date": "2023-05-11"
//}


    @SerializedName("bus-id")
    private Integer busId;
    @SerializedName("schedule-id")
    private Integer scheduleId;
    @SerializedName("date")
    private String date;
    @SerializedName("employee-id")
    private Integer driver_id;

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public Integer getBusId(Integer busId) {
        return this.busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getScheduleId(Integer scheduleId) {
        return this.scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDate(String selectedDate) {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
