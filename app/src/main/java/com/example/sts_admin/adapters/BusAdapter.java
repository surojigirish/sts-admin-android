package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.Bus;

import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ViewHolder> {
    List<Bus> busList;
    Context context;

    OnBusItemClickListener onBusItemClickListener;

    public BusAdapter(List<Bus> busList, Context context, OnBusItemClickListener onBusItemClickListener) {
        this.onBusItemClickListener = onBusItemClickListener;
        this.busList = busList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.bus_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvRegNo.setText(busList.get(position).getRegNo());
        holder.tvBusType.setText(busList.get(position).getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = holder.getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION && onBusItemClickListener != null){
                    Bus selectedBus = busList.get(pos);
                    Integer busId = selectedBus.getId();
                    String busRegNo = selectedBus.getRegNo();
                    onBusItemClickListener.onClickListener(busId,busRegNo);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRegNo,tvBusType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRegNo = itemView.findViewById(R.id.textView_list_bus_regNo);
            tvBusType = itemView.findViewById(R.id.textView_list_bus_type);
        }
    }

    public interface OnBusItemClickListener{
        void onClickListener(Integer busId,String busRegNo);
    }
}
