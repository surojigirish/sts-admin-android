package com.example.sts_admin.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.activity.DriverDashboard;
import com.example.sts_admin.model.Result;

import java.util.List;

public class BusScheduleListAdapter extends RecyclerView.Adapter<BusScheduleListAdapter.ViewHolder> {

    List<Result> busScheduleList;
    Context context;

    private OnItemClickListener onItemClickListener;

    public BusScheduleListAdapter(List<Result> busScheduleList, Context context, OnItemClickListener onItemClickListener) {
        this.busScheduleList = busScheduleList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.bus_schedule_list_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.availableSeats.setText(busScheduleList.get(position).getAvailableSeats());
        holder.busId.setText(busScheduleList.get(position).getBusId().toString());
        holder.date.setText(busScheduleList.get(position).getDate());
        holder.scheduleId.setText(busScheduleList.get(position).getScheduleId().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "CLICKED", Toast.LENGTH_SHORT).show();
                Integer pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && onItemClickListener != null) {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick("barcode");
//                }
                    Result selectedList= busScheduleList.get(pos);
                    String driverBusId = selectedList.getBusId().toString();
                    String driverScheduleId=selectedList.getScheduleId().toString();

                    onItemClickListener.onItemClick(driverBusId,driverScheduleId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return busScheduleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView availableSeats, busId, date, scheduleId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            availableSeats = itemView.findViewById(R.id.available_seats);
            busId = itemView.findViewById(R.id.bus_id);
            date = itemView.findViewById(R.id.date);
            scheduleId = itemView.findViewById(R.id.schedule_id);
        }
    }


        public interface OnItemClickListener {
            void onItemClick(String driverBusId, String driverScheduleId);
        }
}
