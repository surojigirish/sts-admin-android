package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.RouteInfoResult;

import java.util.List;

public class GetRouteInfoDetailsAdapter extends RecyclerView.Adapter<GetRouteInfoDetailsAdapter.ViewHolder> {

    Context context;

    List<RouteInfoResult> routeInfoResultList;

    OnRouteInfoClickListener onRouteInfoClickListener;

    // Delete Click Listener instance
    private OnDeleteClickListener onDeleteClickListener;
    private RecyclerView recyclerView;


    public GetRouteInfoDetailsAdapter(Context context, List<RouteInfoResult> routeInfoResultList, OnRouteInfoClickListener onRouteInfoClickListener) {
        this.context = context;
        this.routeInfoResultList = routeInfoResultList;
        this.onRouteInfoClickListener = onRouteInfoClickListener;
    }

    public GetRouteInfoDetailsAdapter(Context context, List<RouteInfoResult> routeInfoResultList, RecyclerView recyclerView) {
        this.context = context;
        this.routeInfoResultList = routeInfoResultList;
        this.recyclerView = recyclerView;
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

        holder.tvRouteInfoSource.setText("From : "+routeInfoResultList.get(position).getRouteSource().getName());
        holder.tvRouteInfoDestination.setText("To : "+routeInfoResultList.get(position).getDestination().getName());
        holder.tvRouteDistance.setText("Distence : "+routeInfoResultList.get(position).getDistance()+" km");
        holder.tvRouteFare.setText("Fare"+routeInfoResultList.get(position).getFare());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && onRouteInfoClickListener != null ){
                    RouteInfoResult selectedRouteInfo = routeInfoResultList.get(pos);
//                    String infoRouteBusType = selectedRouteInfo.getRouteId().toString();
                    String infoRouteDistance = selectedRouteInfo.getDistance();
                    String infoFare = selectedRouteInfo.getFare();

                    onRouteInfoClickListener.onRouteClick(infoRouteDistance,infoFare);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return routeInfoResultList.size();
    }


    // onDeleteClickListener setter
    public void setOnDeleteClickListener(GetRouteInfoDetailsAdapter.OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvRouteInfoSource, tvRouteInfoDestination, tvRouteDistance, tvRouteFare;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRouteInfoSource = itemView.findViewById(R.id.tv_route_info_source);
            tvRouteInfoDestination= itemView.findViewById(R.id.tv_route_info_destination);
            tvRouteDistance= itemView.findViewById(R.id.tv_route_distance);
            tvRouteFare= itemView.findViewById(R.id.tv_route_fare);
        }
    }

    public interface OnRouteInfoClickListener{
        void onRouteClick(String infoRouteDistance,String infoFare);
    }

    // onDeleteClickListener interface
    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    // ItemTouchHelper interface
    // Implement ItemTouchHelper.SimpleCallBack separately
    private ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();

            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(position);
            }
        }
    };

    // Associate the ItemTouchHelper with the RecyclerView
    public void enableSwipeToDelete() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
