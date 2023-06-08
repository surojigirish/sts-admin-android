package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.results.TicketResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OnBoardPassengerResponse {
    @SerializedName("tickets")
    private List<TicketResult> ticketResultList;
    @SerializedName("status")
    private Integer status;
    @SerializedName("success")
    private Boolean success;

    public List<TicketResult> getTicketResultList() {
        return ticketResultList;
    }

    public void setTicketResultList(List<TicketResult> ticketResultList) {
        this.ticketResultList = ticketResultList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}

