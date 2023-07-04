package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.Schedule;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    List<Schedule> scheduleList;
    Context context;
    OnScheduleItemClickListener onScheduleItemClickListener;

    public ScheduleAdapter(List<Schedule> scheduleList, Context context, OnScheduleItemClickListener onScheduleItemClickListener) {
        this.scheduleList = scheduleList;
        this.context = context;
        this.onScheduleItemClickListener = onScheduleItemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.schedule_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//<<<<<<< HEAD
//        holder.tvScheduleSource.setText("source : "+scheduleList.get(position).getRouteR().getSource().getName());
//        holder.tvScheduleDest.setText("Destination : "+scheduleList.get(position).getRouteR().getDestination().getName());
//        holder.tvArrival.setText("Arrival at : "+scheduleList.get(position).getArrival());
//        holder.tvDeparture.setText("Departure at : "+scheduleList.get(position).getDeparture());
//=======
        holder.tvScheduleSource.setText("source : "+scheduleList.get(position).getRoute().getSource().getHaltName());
        holder.tvScheduleDest.setText("Destination : "+scheduleList.get(position).getRoute().getDestination().getHaltName());
        holder.tvArrival.setText("Arrival at : "+scheduleList.get(position).getArrivalAt());
        holder.tvDeparture.setText("Departure at : "+scheduleList.get(position).getDepartureAt());
//>>>>>>> 0b6d6d98c1a0ca707dbb7c5ebce63d2f19a0af09

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = holder.getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION && onScheduleItemClickListener != null){
                    Schedule selectedSchedule = scheduleList.get(pos);
                    Integer scheduleId = selectedSchedule.getId();
//<<<<<<< HEAD
//                    String scheduleSource = selectedSchedule.getRouteR().getSource().getName();
//                    String scheduleDestnation = selectedSchedule.getRouteR().getDestination().getName();
//=======
                    String scheduleSource = selectedSchedule.getRoute().getSource().getHaltName();
                    String scheduleDestnation = selectedSchedule.getRoute().getDestination().getHaltName();
//>>>>>>> 0b6d6d98c1a0ca707dbb7c5ebce63d2f19a0af09
                    onScheduleItemClickListener.onClickListener(scheduleId,scheduleSource,scheduleDestnation);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvScheduleSource, tvScheduleDest, tvArrival, tvDeparture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvScheduleSource = itemView.findViewById(R.id.textView_sche_source);
            tvScheduleDest = itemView.findViewById(R.id.textView_sche_destination);
            tvArrival = itemView.findViewById(R.id.textView_sche_arrival);
            tvDeparture = itemView.findViewById(R.id.textView_sche_departure);


        }
    }

    public interface OnScheduleItemClickListener {
        void onClickListener(Integer scheduleId,String scheSource,String scheDestination);
    }
}
