package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class ScheduleR {

    @SerializedName("arrival") private String arrival;
    @SerializedName("departure") private String departure;
    @SerializedName("id") private Integer id;
    @SerializedName("route") private RouteR routeR;
    @SerializedName("ticket") private TicketR ticketR;

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RouteR getRouteR() {
        return routeR;
    }

    public void setRouteR(RouteR routeR) {
        this.routeR = routeR;
    }

    public TicketR getTicketR() {
        return ticketR;
    }

    public void setTicketR(TicketR ticketR) {
        this.ticketR = ticketR;
    }
}
