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
import com.example.sts_admin.model.AddRouteDetails;

import java.util.List;

public class GetRouteDetailsAdapter extends RecyclerView.Adapter<GetRouteDetailsAdapter.ViewHolder> {

    List<AddRouteDetails> addRouteDetailsList;
    Context context;

    // Delete Click Listener instance
    private OnDeleteClickListener onDeleteClickListener;
    private RecyclerView recyclerView;

    public GetRouteDetailsAdapter(List<AddRouteDetails> addRouteDetailsList, Context context) {
        this.addRouteDetailsList = addRouteDetailsList;
        this.context = context;
    }

    public GetRouteDetailsAdapter(List<AddRouteDetails> addRouteDetailsList, Context context, RecyclerView recyclerView) {
        this.addRouteDetailsList = addRouteDetailsList;
        this.context = context;
        this.recyclerView = recyclerView;
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

    // onDeleteClickListener setter
    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{


        TextView getRouteSource, getRouteDestination;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            getRouteSource = itemView.findViewById(R.id.tvRouteSourceDisplay);
            getRouteDestination = itemView.findViewById(R.id.tvRouteDestinationDisplay);
        }
    }


    // onDeleteClickListener interface
    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    // onClickRouteInfo interface
    public interface onItemClickListener {
        void onItemClick();
    }

    // ItemTouchHelper interface
    // Implement ItemTouchHelper.SimpleCallBack separately
    private ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
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
