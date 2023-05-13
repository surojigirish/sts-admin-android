package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.Schedule;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleResponse {

//
//    "message": "schedule info added successfully",
//            "schedule": {
//        "arrival-time": "20:00",
//                "departure-time": "17:00",
//                "duration": "45",
//                "schedule-id": 8
//    },
//            "status": 200,
//            "success": true


    @SerializedName("message")
    private String message;

    @SerializedName("schedule")
    private Schedule schedule;

    @SerializedName("status")
    private Integer status;

    @SerializedName("success")
    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
