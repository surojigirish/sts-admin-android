package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class Ticket {
//    "ticket": {
//                "booked_at": "2023-06-08 18:18:41",
//                "destination": "Margao",
//                "distance-travelled": "5.51",
//                "fare-amount": 6,
//                "id": 22,
//                "passenger-count": 1,
//                "source": "DARRON",
//                "status": "Booked"
//            }

    @SerializedName("booked_at")
    private String booked_at;
    @SerializedName("destination")
    private String destination;
    @SerializedName("distance-travelled")
    private String distanceTravelled;
    @SerializedName("fare-amount")
    private Integer fareAmount;
    @SerializedName("id")
    private Integer ticketId;
    @SerializedName("passenger-count")
    private Integer passengerCount;
    @SerializedName("source")
    private String source;
    @SerializedName("status")
    private String status;

    public String getBooked_at() {
        return booked_at;
    }

    public void setBooked_at(String booked_at) {
        this.booked_at = booked_at;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(String distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public Integer getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(Integer fareAmount) {
        this.fareAmount = fareAmount;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
