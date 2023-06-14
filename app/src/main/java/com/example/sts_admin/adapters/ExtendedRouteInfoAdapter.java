package com.example.sts_admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.RouteInfoResult;

import java.util.List;

public class ExtendedRouteInfoAdapter extends RecyclerView.Adapter<ExtendedRouteInfoAdapter.ViewHolder> {

    // List of routes info
    private final List<RouteInfoResult> routeInfoList;

    public ExtendedRouteInfoAdapter(List<RouteInfoResult> routeInfoList) {
        this.routeInfoList = routeInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.extended_route_info_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Source
        String source = routeInfoList.get(position).getRouteSource().getName();
        String sourceText = "Source : " + source;
        holder.tvSource.setText(sourceText);

        // Destination
        String destination = routeInfoList.get(position).getDestination().getName();
        String destinationText = "Destination : " + destination;
        holder.tvDestination.setText(destinationText);

        // Fare
        String fare = routeInfoList.get(position).getFare();
        String fareText = "Fare : " + fare + " Rs.";
        holder.tvFare.setText(fareText);

        // Distance
        String distance = routeInfoList.get(position).getDistance();
        String distanceText = "Distance : " + distance + " km";
        holder.tvDistance.setText(distanceText);
    }

    @Override
    public int getItemCount() {
        return routeInfoList.size();
    }

    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // TextViews
        TextView tvSource, tvDestination, tvFare, tvDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views
            initializeViews(itemView);
        }

        private void initializeViews(View view) {
            tvSource = view.findViewById(R.id.tv_routeInfo_source);
            tvDestination = view.findViewById(R.id.tv_routeInfo_destination);
            tvFare = view.findViewById(R.id.tv_routeInfo_fare);
            tvDistance = view.findViewById(R.id.tv_routeInfo_distance);
        }
    }
}
