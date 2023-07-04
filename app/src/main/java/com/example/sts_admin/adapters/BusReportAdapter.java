package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.ScheduleR;

import java.util.List;

public class BusReportAdapter extends RecyclerView.Adapter<BusReportAdapter.ViewHolder> {

    List<ScheduleR> schedulesList;
    Context context;

    public BusReportAdapter(List<ScheduleR> resultReportList, Context context) {
        this.schedulesList = resultReportList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.bus_report_items,
                        parent,
                        false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.tvSource.setText(schedulesList.get(position).getRouteR().getSource().getName());
        holder.tvDestination.setText(schedulesList.get(position).getRouteR().getDestination().getName());
        holder.tvScheduleAt.setText(schedulesList.get(position).getDeparture());
        holder.tvArrivalAt.setText(schedulesList.get(position).getArrival());
        holder.tvPassengerCount.setText(String.valueOf(schedulesList.get(position).getTicketR().getPassengerCount()));
        holder.tvAmount.setText(String.valueOf(schedulesList.get(position).getTicketR().getTotalFareAmount()));
        holder.tvTicketCount.setText(String.valueOf(schedulesList.get(position).getTicketR().getTotalTickets()));
    }

    @Override
    public int getItemCount() {
        return schedulesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        TextView tvSource, tvDestination, tvScheduleAt, tvArrivalAt, tvPassengerCount, tvAmount, tvTicketCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSource = itemView.findViewById(R.id.source);
            tvDestination = itemView.findViewById(R.id.destination);
            tvScheduleAt = itemView.findViewById(R.id.tvScheduleAt);
            tvArrivalAt = itemView.findViewById(R.id.tvArrivalAt);
            tvPassengerCount = itemView.findViewById(R.id.tvpassengerCount);
            tvAmount = itemView.findViewById(R.id.tvAmountGenerated);
            tvTicketCount = itemView.findViewById(R.id.tvTicketCount);
        }
    }
}
