package com.example.sts_admin.model.results;

import com.example.sts_admin.model.Bus;
import com.example.sts_admin.model.BusSchedule;
import com.example.sts_admin.model.Location;
import com.google.gson.annotations.SerializedName;

public class ListOfBusSchedule {

    @SerializedName("bus")
    private Bus bus;
    @SerializedName("location")
    private Location location;
    @SerializedName("schedule-info")
    private BusSchedule busSchedule;

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public BusSchedule getBusSchedule() {
        return busSchedule;
    }

    public void setBusSchedule(BusSchedule busSchedule) {
        this.busSchedule = busSchedule;
    }
}
