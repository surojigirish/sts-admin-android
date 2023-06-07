package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.RouteInfoResult;

import java.util.List;

public class GetRouteInfoDetailsAdapter extends RecyclerView.Adapter<GetRouteInfoDetailsAdapter.ViewHolder> {

    Context context;

    List<RouteInfoResult> routeInfoResultList;

    public GetRouteInfoDetailsAdapter(Context context, List<RouteInfoResult> routeInfoResultList) {
        this.context = context;
        this.routeInfoResultList = routeInfoResultList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.route_info_details,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvRouteInfoSource.setText(routeInfoResultList.get(position).getRouteSource().getName());
        holder.tvRouteInfoDestination.setText(routeInfoResultList.get(position).getDestination().getName());

    }

    @Override
    public int getItemCount() {
        return routeInfoResultList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvRouteInfoSource, tvRouteInfoDestination;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRouteInfoSource = itemView.findViewById(R.id.tv_route_info_source);
            tvRouteInfoDestination= itemView.findViewById(R.id.tv_route_info_destination);
        }
    }
}
