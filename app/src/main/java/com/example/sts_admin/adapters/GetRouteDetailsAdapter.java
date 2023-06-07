package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.AddRouteDetails;

import java.util.List;

public class GetRouteDetailsAdapter extends RecyclerView.Adapter<GetRouteDetailsAdapter.ViewHolder> {

    List<AddRouteDetails> addRouteDetailsList;
    Context context;

    public GetRouteDetailsAdapter(List<AddRouteDetails> addRouteDetailsList, Context context) {
        this.addRouteDetailsList = addRouteDetailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.add_route_details_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.getRouteSource.setText(addRouteDetailsList.get(position).getRouteSource().getName());
        holder.getRouteDestination.setText(addRouteDetailsList.get(position).getDestination().getName());


    }

    @Override
    public int getItemCount() {
        return addRouteDetailsList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{


        TextView getRouteSource, getRouteDestination;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            getRouteSource = itemView.findViewById(R.id.tvRouteSourceDisplay);
            getRouteDestination = itemView.findViewById(R.id.tvRouteDestinationDisplay);
        }
    }

}
