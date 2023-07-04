package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.results.ResultReport;

import java.util.List;

public class BusReportAdapter extends RecyclerView.Adapter<BusReportAdapter.ViewHolder> {

    List<ResultReport> resultReportList;
    Context context;

    public BusReportAdapter(List<ResultReport> resultReportList, Context context) {
        this.resultReportList = resultReportList;
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


        holder.tvSource.setText(resultReportList.get(position).getScheduleR().getRouteR().getSource().getName());
        holder.tvDestination.setText(resultReportList.get(position).getScheduleR().getRouteR().getDestination().getName());
        holder.tvScheduleAt.setText(resultReportList.get(position).getScheduleR().getDeparture());
        holder.tvArrivalAt.setText(resultReportList.get(position).getScheduleR().getArrival());
        holder.tvPassengerCount.setText(resultReportList.get(position).getScheduleR().getTicketR().getPassengerCount());
        holder.tvAmount.setText(resultReportList.get(position).getScheduleR().getTicketR().getTotalFareAmount());
        holder.tvTicketCount.setText(resultReportList.get(position).getScheduleR().getTicketR().getTotalTickets());
    }

    @Override
    public int getItemCount() {
        return resultReportList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


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
