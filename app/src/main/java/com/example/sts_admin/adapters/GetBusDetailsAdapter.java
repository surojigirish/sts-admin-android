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

    public GetBusDetailsAdapter(Context context, List<BusResult> busResultList) {
        this.context = context;
        this.busResultList = busResultList;
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


}
