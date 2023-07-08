package com.example.sts_admin.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.results.ResultRouteSchedule;

import java.util.List;

public class RouteBasedScheduleAdapter extends RecyclerView.Adapter<RouteBasedScheduleAdapter.ViewHolder> {
    List<ResultRouteSchedule> resultRouteSchedule;
    Context context;

    OnItemClickListener onItemClickListener;

    public RouteBasedScheduleAdapter(List<ResultRouteSchedule> resultRouteSchedule, Context context, OnItemClickListener onItemClickListener) {
        this.resultRouteSchedule = resultRouteSchedule;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.route_based_schedule_item,
                        parent,
                        false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvSource.setText(resultRouteSchedule.get(position).getSource().getName());
        holder.tvDestination.setText(resultRouteSchedule.get(position).getDestination().getName());
        holder.tvScheduleAt.setText(resultRouteSchedule.get(position).getDepartureAt());
        holder.tvArrivalAt.setText(resultRouteSchedule.get(position).getArrivalAt());
        holder.tvDuration.setText(resultRouteSchedule.get(position).getDuration());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION && onItemClickListener != null){
                    ResultRouteSchedule selectedSchedule = resultRouteSchedule.get(pos);

                    String id = selectedSchedule.getId();
                    String source = selectedSchedule.getSource().getName();
                    String destination = selectedSchedule.getDestination().getName();
                    String arrivalT = selectedSchedule.getArrivalAt();
                    String departureT = selectedSchedule.getDepartureAt();
                    String duration = selectedSchedule.getDuration();


                    onItemClickListener.onScheduleItemClick(id,source,destination,arrivalT,departureT,duration);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultRouteSchedule.size();
    }


    public  class  ViewHolder extends RecyclerView.ViewHolder{

        TextView tvSource, tvDestination, tvScheduleAt, tvArrivalAt, tvDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSource = itemView.findViewById(R.id.source);
            tvDestination = itemView.findViewById(R.id.destination);
            tvScheduleAt = itemView.findViewById(R.id.tvScheduleAt);
            tvArrivalAt = itemView.findViewById(R.id.tvArrivalAt);
            tvDuration = itemView.findViewById(R.id.tvDuration);
        }
    }


    // OnClick listener interface
    public interface OnItemClickListener {
        void onScheduleItemClick(String id, String source, String destination, String arrivalTime,String departureTime,String duration);
    }



}
