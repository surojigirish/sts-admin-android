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

public class ShowRouteAdapter extends RecyclerView.Adapter<ShowRouteAdapter.ViewHolder> {
    List<AddRouteDetails> addRouteDetailsList;
    Context context;

    OnRouteItemClickListener onRouteItemClickListener;

    public ShowRouteAdapter(List<AddRouteDetails> addRouteDetailsList, Context context, OnRouteItemClickListener onRouteItemClickListener) {
        this.addRouteDetailsList = addRouteDetailsList;
        this.context = context;
        this.onRouteItemClickListener = onRouteItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.show_route_list_item,parent,false);

        return new ViewHolder(view, addRouteDetailsList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.routeSource.setText(addRouteDetailsList.get(position).getRouteSource().getName());
        holder.routeDestination.setText(addRouteDetailsList.get(position).getDestination().getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && onRouteItemClickListener != null){
                    AddRouteDetails selectedRoute = addRouteDetailsList.get(pos);
                    Integer routeId = selectedRoute.getId();
                    String routeSource = selectedRoute.getRouteSource().getName();
                    String routeDest = selectedRoute.getDestination().getName();
                    onRouteItemClickListener.onClickListener(routeId,routeSource,routeDest);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return addRouteDetailsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView routeSource, routeDestination;

        public ViewHolder(@NonNull View itemView, List<AddRouteDetails> addRouteDetailsList) {
            super(itemView);

            routeSource = itemView.findViewById(R.id.tvRouteSource);
            routeDestination = itemView.findViewById(R.id.tvRouteDestination);

        }
    }

    /*-------------  onclick interface to get route id --------------------*/
    public interface OnRouteItemClickListener {
        void onClickListener(Integer routeId, String routeSource, String routeDest);
    }
}
