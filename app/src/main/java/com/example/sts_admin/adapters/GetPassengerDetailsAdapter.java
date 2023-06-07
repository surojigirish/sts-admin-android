package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.PassengerUser;

import java.util.List;

public class GetPassengerDetailsAdapter extends RecyclerView.Adapter<GetPassengerDetailsAdapter.ViewHolder> {

    Context context;
    List<PassengerUser> passengerUsers;

    public GetPassengerDetailsAdapter(Context context, List<PassengerUser> passengerUsers) {
        this.context = context;
        this.passengerUsers = passengerUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.get_passenger_details_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.passengerLastName.setText(passengerUsers.get(position).getFirstname());
        holder.passengerLastName.setText(passengerUsers.get(position).getLastname());
//        holder.passengerPhoto.setText(passengerUserList.get(position).getPhoto());
        holder.passengerUserId.setText(passengerUsers.get(position).getUserId().toString());
    }

    @Override
    public int getItemCount() {
        return passengerUsers.size();
    }


    public static class ViewHolder extends  RecyclerView.ViewHolder{


        TextView passengerFirstName, passengerLastName, passengerPhoto, passengerUserId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            passengerFirstName = itemView.findViewById(R.id.tvPassengerFirstName);
            passengerLastName = itemView.findViewById(R.id.tvPassengerLastName);
//            passengerPhoto = itemView.findViewById(R.id.passengerPhoto);
            passengerUserId = itemView.findViewById(R.id.tvPassengerUserId);
        }
    }

}
