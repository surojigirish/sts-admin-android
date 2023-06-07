package com.example.sts_admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.RouteModel;

import java.util.List;

public class RouteInfoAdapter extends RecyclerView.Adapter<RouteInfoAdapter.ViewHolder> {

    // List of RouteInfo model class
    List<RouteModel> routeInfoList;

    // Listener instance
    OnItemClickListener itemClickListener;

    // Public constructor for RouteInfoAdapter
    public RouteInfoAdapter(List<RouteModel> routeInfoList, OnItemClickListener listener) {
        this.routeInfoList = routeInfoList;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.route_info_list_item,
                        parent,
                        false);
        return new ViewHolder(view, itemClickListener, routeInfoList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RouteModel routeModel = routeInfoList.get(position);
        holder.tvSource.setText(routeModel.getSource().getHaltName());
        holder.tvDestination.setText(routeModel.getDestination().getHaltName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION && itemClickListener != null) {
                    // Get route position to RouteModel
                    RouteModel oSelectRoute = routeInfoList.get(pos);

                    // Store clicked route data to variable
                    int vRouteId = oSelectRoute.getId();
                    String vRouteSource = oSelectRoute.getSource().getHaltName();
                    String vRouteDestination = oSelectRoute.getDestination().getHaltName();

                    // click listener params
                    itemClickListener.onRouteItemClick(vRouteId, vRouteSource, vRouteDestination);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return routeInfoList.size();
    }

    // Custom view holder class for route-info adapter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDestination, tvSource;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener, final List<RouteModel> routeList) {
            super(itemView);

            // Initialize views
            initViews(itemView);
        }

        private void initViews(View view) {
            tvDestination = view.findViewById(R.id.tv_routeInfo_destination_name);
            tvSource = view.findViewById(R.id.tv_routeInfo_source_name);
        }
    }

    // OnClick listener interface
    public interface OnItemClickListener {
        void onRouteItemClick(int id, String source, String destination);
    }
}
