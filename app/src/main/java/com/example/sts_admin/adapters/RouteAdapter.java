package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.Route;
import com.example.sts_admin.model.RouteInfo;

import java.util.List;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder> {

    Context context;
    List<RouteInfo> routeList;

    // Listener instance
    public OnItemClickListener itemClickListener;

    public RouteAdapter(Context context, List<RouteInfo> routeList, OnItemClickListener itemClickListener) {
        this.context = context;
        this.routeList = routeList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.route_list_item, parent, false);

        return new ViewHolder(view, itemClickListener, routeList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RouteInfo route = routeList.get(position);
        holder.tvSourceStand.setText(route.getSourceId().getHaltName());
        holder.tvDestinationStand.setText(route.getDestinationId().getHaltName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();

                if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                    // get route position
                    RouteInfo selectedRoute = routeList.get(position);
                    // store route-id
                    int routeId = selectedRoute.getRouteId();
                    String destination = selectedRoute.getDestinationId().getHaltName();
                    String source = selectedRoute.getSourceId().getHaltName();
                    // call click listener and pass params
                    itemClickListener.onItemClick(routeId, destination, source);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }

    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSourceStand, tvDestinationStand;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener, final List<RouteInfo> routeList) {
            super(itemView);

            // init views
            tvSourceStand = itemView.findViewById(R.id.textView_source_stand);
            tvDestinationStand = itemView.findViewById(R.id.textView_destination_stand);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Integer routeId, String routeDestination, String routeSource);
    }
}
