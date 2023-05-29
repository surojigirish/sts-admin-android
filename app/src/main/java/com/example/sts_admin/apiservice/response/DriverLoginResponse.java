package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.Session;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DriverLoginResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("session")
    private Session session;

    @SerializedName("status")
    private Integer status;

    public DriverLoginResponse(String message, Session session, Integer status) {
        this.message = message;
        this.session = session;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

