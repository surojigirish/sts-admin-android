package com.example.sts_admin.apiservice.response;


import com.example.sts_admin.model.Halts;
import com.google.gson.annotations.SerializedName;

public class HaltResponse {

    private String message;
    private int status;
    private boolean success;

    @SerializedName("user-admin")
    private Halts halts;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Halts getHalts() {
        return halts;
    }

    public void setHalts(Halts halts) {
        this.halts = halts;
    }
}
