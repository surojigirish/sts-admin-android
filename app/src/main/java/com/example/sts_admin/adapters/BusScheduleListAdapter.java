package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.results.ListOfBusSchedule;

import java.util.List;

public class BusScheduleListAdapter extends RecyclerView.Adapter<BusScheduleListAdapter.ViewHolder> {

    List<ListOfBusSchedule> driverBusSchedule;

    // Click listener
    private OnDriverBusScheduleClick onDriverBusScheduleClick;

    public BusScheduleListAdapter(List<ListOfBusSchedule> driverBusSchedule, OnDriverBusScheduleClick onDriverBusScheduleClick) {
        this.driverBusSchedule = driverBusSchedule;
        this.onDriverBusScheduleClick = onDriverBusScheduleClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bus_schedule_list_items, parent, false);

        return new ViewHolder(view, onDriverBusScheduleClick, driverBusSchedule);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.busScheduleId.setText(String.valueOf(driverBusSchedule.get(position).getBusSchedule().getId()));
        holder.busRegistration.setText(driverBusSchedule.get(position).getBus().getRegistrationNumber());
        holder.busType.setText(driverBusSchedule.get(position).getBus().getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION && onDriverBusScheduleClick != null) {
                    ListOfBusSchedule busSchedule = driverBusSchedule.get(pos);

                    // pass bus schedule id
                    Integer busScheduleId = busSchedule.getBusSchedule().getId();

                    // Click handler
                    onDriverBusScheduleClick.onItemClick(busScheduleId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return driverBusSchedule.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView busScheduleId, busRegistration, busType;

        public ViewHolder(@NonNull View itemView, OnDriverBusScheduleClick listener, List<ListOfBusSchedule> driverBusScheduleList) {
            super(itemView);
            busScheduleId = itemView.findViewById(R.id.tv_busScheduleId);
            busRegistration = itemView.findViewById(R.id.tv_busRegistration);
            busType = itemView.findViewById(R.id.tv_busType);
        }
    }


    public interface OnDriverBusScheduleClick {
        void onItemClick(Integer busScheduleId);
    }
}
