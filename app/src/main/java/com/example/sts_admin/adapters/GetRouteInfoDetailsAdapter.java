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

    OnRouteInfoClickListener onRouteInfoClickListener;

    public GetRouteInfoDetailsAdapter(Context context, List<RouteInfoResult> routeInfoResultList, OnRouteInfoClickListener onRouteInfoClickListener) {
        this.context = context;
        this.routeInfoResultList = routeInfoResultList;
        this.onRouteInfoClickListener = onRouteInfoClickListener;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && onRouteInfoClickListener != null ){
                    RouteInfoResult selectedRouteInfo = routeInfoResultList.get(pos);
                    String infoRouteBusType = selectedRouteInfo.getRouteId().toString();
                    String infoRouteDistance = selectedRouteInfo.getDistance();
                    String infoFare = selectedRouteInfo.getFare();

                    onRouteInfoClickListener.onRouteClick(infoRouteBusType,infoRouteDistance,infoFare);
                }
            }
        });

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

    public interface OnRouteInfoClickListener{
        void onRouteClick(String infoRouteBusType, String infoRouteDistance,String infoFare);
    }
}
