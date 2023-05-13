package com.example.sts_admin.apiservice.response;


import com.example.sts_admin.model.Halts;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HaltResponse {

    private String message;
    private int status;
    private boolean success;

    @SerializedName("result")
    private List<Halts> result;


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

    public List<Halts> getResult() {
        return result;
    }

    public void setResult(List<Halts> result) {
        this.result = result;
    }
}
