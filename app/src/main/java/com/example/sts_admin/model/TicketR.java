package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class TicketR {
    @SerializedName("passenger-count") Integer passengerCount;
    @SerializedName("total-fare-amount") Integer totalFareAmount;
    @SerializedName("total-tickets") Integer totalTickets;

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    public Integer getTotalFareAmount() {
        return totalFareAmount;
    }

    public void setTotalFareAmount(Integer totalFareAmount) {
        this.totalFareAmount = totalFareAmount;
    }

    public Integer getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(Integer totalTickets) {
        this.totalTickets = totalTickets;
    }
}
