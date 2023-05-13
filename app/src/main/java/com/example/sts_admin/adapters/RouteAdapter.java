package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.Routes;

import java.util.List;

import okhttp3.Route;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder> {

    List<Routes> routesList;
    Context context;

    OnRouteItemClickListener onRouteItemClickListener;

    public RouteAdapter(List<Routes> routesList, Context context, OnRouteItemClickListener onRouteItemClickListener) {
        this.routesList = routesList;
        this.context = context;
        this.onRouteItemClickListener = onRouteItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.routeitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.source.setText(routesList.get(position).getSource());
        holder.destination.setText(routesList.get(position).getDestination());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer position=holder.getAdapterPosition();

                if (position!=RecyclerView.NO_POSITION && onRouteItemClickListener!=null){
                    Routes selectedRoute=routesList.get(position);
                    Integer routeId= selectedRoute.getRouteId();
                    String source= selectedRoute.getSource();
                    String destination= selectedRoute.getDestination();
                    onRouteItemClickListener.onClickListener(routeId,source,destination);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return routesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView source,destination;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            source=itemView.findViewById(R.id.routeSource);
            destination=itemView.findViewById(R.id.routeDestination);



        }
    }

    public interface OnRouteItemClickListener{
        void onClickListener(Integer routeId,String source, String destination);
    }
}
