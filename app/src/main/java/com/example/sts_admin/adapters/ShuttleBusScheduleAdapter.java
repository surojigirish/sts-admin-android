package com.example.sts_admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.results.ListOfBusSchedule;

import java.util.List;

public class ShuttleBusScheduleAdapter extends RecyclerView.Adapter<ShuttleBusScheduleAdapter.ViewHolder> {

    // initialize list
    private List<ListOfBusSchedule> busScheduleList;

    // On click listener
    private OnBusScheduleClickListener onBusScheduleClickListener;

    public ShuttleBusScheduleAdapter(List<ListOfBusSchedule> busScheduleList, OnBusScheduleClickListener onBusScheduleClickListener) {
        this.busScheduleList = busScheduleList;
        this.onBusScheduleClickListener = onBusScheduleClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.pass_bus_schedule_list_item,
                        parent,
                        false
                );

        return new ViewHolder(view, onBusScheduleClickListener, busScheduleList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // set Date
        holder.date.setText(busScheduleList.get(position).getBusSchedule().getDate());
        // set bus details
        holder.busType.setText(busScheduleList.get(position).getBus().getType());
        holder.busRegistration.setText(busScheduleList.get(position).getBus().getRegistrationNumber());
        // set schedule data
        holder.departureBusStand.setText(busScheduleList.get(position).getBusSchedule().getSchedule().getDepartureBusStand());
        holder.departureTime.setText(busScheduleList.get(position).getBusSchedule().getSchedule().getDepartureTime());
        holder.arrivalBusStand.setText(busScheduleList.get(position).getBusSchedule().getSchedule().getArrivalBusStand());
        holder.arrivalTime.setText(busScheduleList.get(position).getBusSchedule().getSchedule().getArrivalTime());
        // seat and duration data
        holder.availableSeats.setText(String.valueOf(busScheduleList.get(position).getBusSchedule().getSeatsAvailable()));
        holder.durationOfTravel.setText(busScheduleList.get(position).getBusSchedule().getSchedule().getDuration());

        // On item click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION && onBusScheduleClickListener != null) {
                    ListOfBusSchedule busSchedule = busScheduleList.get(pos);

                    // pass bus schedule id and date
                    Integer busScheduleId = busSchedule.getBusSchedule().getId();
                    String date = busSchedule.getBusSchedule().getDate();

                    // pass the data to onClick handler
                    onBusScheduleClickListener.onItemClick(busScheduleId, date);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return busScheduleList.size();
    }

    // Create a ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Declare variables that will hold data for the bus schedule list item
        TextView departureTime, arrivalTime, departureBusStand, arrivalBusStand, availableSeats, date, durationOfTravel, busRegistration, busType;

        public ViewHolder(@NonNull View itemView, OnBusScheduleClickListener listener, List<ListOfBusSchedule> busScheduleList) {
            super(itemView);

            // init views
            initializeViewsForHolder(itemView);
        }

        // Function to init views
        private void initializeViewsForHolder(View v) {
            // Initialising views from pass_bus_schedule_list_item.xml
            departureTime = v.findViewById(R.id.tvDepartureTime);
            departureBusStand = v.findViewById(R.id.tvDepartureBusStand);

            arrivalTime = v.findViewById(R.id.tvArrivalTime);
            arrivalBusStand = v.findViewById(R.id.tvArrivalBusStand);

            busRegistration = v.findViewById(R.id.tvBusNo);
            busType = v.findViewById(R.id.tvBusType);

            availableSeats = v.findViewById(R.id.tvSeatsAvailable);

            durationOfTravel = v.findViewById(R.id.tvDuration);
            date = v.findViewById(R.id.tvDateOfSchedule);
        }
    }

    // Interface to set onClickListener
    public interface OnBusScheduleClickListener {
        void onItemClick(Integer busId, String scheduleDate);
    }
}
