package com.example.sts_admin.model.results;

import com.example.sts_admin.model.Passenger;
import com.example.sts_admin.model.Ticket;
import com.google.gson.annotations.SerializedName;

public class TicketResult {
    @SerializedName("passenger")
    private Passenger passenger;
    @SerializedName("ticket")
    private Ticket ticket;

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
