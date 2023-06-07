package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.model.BusResult;

import java.util.List;

public class GetBusDetailsAdapter extends RecyclerView.Adapter<GetBusDetailsAdapter.ViewHolder> {
    Context context;
    List<BusResult> busResultList;

    OnBusDetailsClickListener onBusDetailsClickListener;

    public GetBusDetailsAdapter(Context context, List<BusResult> busResultList, OnBusDetailsClickListener onBusDetailsClickListener) {
        this.context = context;
        this.busResultList = busResultList;
        this.onBusDetailsClickListener = onBusDetailsClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.bus_details,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.registrationNo.setText(busResultList.get(position).getRegistrationNo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && onBusDetailsClickListener !=null){
                  BusResult selectedBus = busResultList.get(pos);

                  Integer busCapacity = selectedBus.getCapacity();
                    Integer busId= selectedBus.getId();
                  String busStatus = selectedBus.getStatus();
                  String busType = selectedBus.getType();

                  onBusDetailsClickListener.onBusDetailsClick(busCapacity,busId,busStatus,busType);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return busResultList.size();
    }


    public static class ViewHolder extends  RecyclerView.ViewHolder{

        TextView registrationNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            registrationNo  = itemView.findViewById(R.id.tv_registration_no);
        }
    }


    public interface OnBusDetailsClickListener{
        void onBusDetailsClick(Integer busCapacity, Integer busId, String busStatus, String busType);

    }


}
