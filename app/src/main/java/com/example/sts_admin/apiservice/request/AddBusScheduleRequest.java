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

    @SerializedName("employee-id")
    private Integer driverid;
    @SerializedName("date")
    private String date;

    public Integer getDriverid() {
        return driverid;
    }

    public void setDriverid(Integer driverid) {
        this.driverid = driverid;
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
