package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.Schedule;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleResponse {

    @SerializedName("result")
    List<Schedule> scheduleList;

    private @SerializedName("status")
    Integer status;
    private @SerializedName("success")
    boolean success;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }


//    public Schedule getScheduleList() {
//        return scheduleList;
//    }
//
//    public void setScheduleList(Schedule scheduleList) {
//        this.scheduleList = scheduleList;
//    }
}
