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
import com.example.sts_admin.model.Passengers;

import java.util.List;

public class GetPassengerListAdapter extends RecyclerView.Adapter<GetPassengerListAdapter.ViewHolder> {

    Context context;
    List<Passengers> passengerUsers;
    OnPassengerItemClickListener onPassengerItemClickListener;

    public GetPassengerListAdapter(Context context, List<Passengers> passengerUsers, OnPassengerItemClickListener onPassengerItemClickListener) {
        this.context = context;
        this.passengerUsers = passengerUsers;
        this.onPassengerItemClickListener = onPassengerItemClickListener;
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
        holder.passengerFirstName.setText(passengerUsers.get(position).getPassengerDetail().getFirstName());
        holder.passengerLastName.setText(passengerUsers.get(position).getPassengerDetail().getLastName());
//        holder.passengerUserId.setText(passengerUsers.getPassengerDetail().get(position).getId());
//        holder.passengerAddress.setText(passengerUsers.get(position).getPassengerDetail().getAddress());
//        holder.passengerGender.setText(passengerUsers.get(position).getPassengerDetail().getGender());
//        holder.passengerDob.setText(passengerUsers.get(position).getPassengerDetail().getDob());
//        holder.passengerContact.setText(passengerUsers.get(position).getPassengerDetail().getContact());
//        holder.passengerCategory.setText(passengerUsers.get(position).getPassengerDetail().getCategory());
//        holder.passengerImage.setText(passengerUsers.get(position).getPassengerDetail().getPhoto());
//        holder.passengerEmail.setText(passengerUsers.get(position).getUserDetails().getEmail());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && onPassengerItemClickListener != null){
                    Passengers selectedPassenger = passengerUsers.get(pos);
                    Integer passengerId = selectedPassenger.getPassengerDetail().getId();
                    String passengerName = selectedPassenger.getPassengerDetail().getFirstName()+" "+selectedPassenger.getPassengerDetail().getLastName();
                    String passengerAddress = selectedPassenger.getPassengerDetail().getAddress();
                    String passengerGender = selectedPassenger.getPassengerDetail().getGender();
                    String passengerCategory = selectedPassenger.getPassengerDetail().getCategory();
                    String passengerContact = selectedPassenger.getPassengerDetail().getContact();
                    String passengerEmailId = selectedPassenger.getUserDetails().getEmail();
                    String passengerPhoto = selectedPassenger.getPassengerDetail().getPhoto();
                    onPassengerItemClickListener.onClickListener(passengerId,passengerName,passengerAddress,passengerGender,passengerCategory,passengerContact,passengerEmailId, passengerPhoto);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return passengerUsers.size();
    }


    public static class ViewHolder extends  RecyclerView.ViewHolder{


        TextView passengerAddress,passengerCategory,passengerContact,passengerDob,passengerFirstName,passengerGender, passengerLastName, passengerEmail, passengerImage, passengerUserId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            passengerFirstName = itemView.findViewById(R.id.passengerFirstName);
            passengerLastName = itemView.findViewById(R.id.passengerLastName);
//            passengerAddress = itemView.findViewById(R.id.passengerAddress);
//            passengerCategory = itemView.findViewById(R.id.passengerCategory);
//            passengerContact = itemView.findViewById(R.id.passengerContact);
//            passengerDob = itemView.findViewById(R.id.passengerDob);
//            passengerGender = itemView.findViewById(R.id.passengerGender);
//            passengerEmail = itemView.findViewById(R.id.passengerEmail);
//            passengerImage = itemView.findViewById(R.id.passengerImage);
////            passengerPhoto = itemView.findViewById(R.id.passengerPhoto);
//            passengerUserId = itemView.findViewById(R.id.tvPassengerUserId);
        }
    }

    /*-------------  onclick interface to get route id --------------------*/
    public interface OnPassengerItemClickListener {
        void onClickListener(Integer passengerId,String passengerName,String passengerAddress,String passengerGender,String passengerCategory,String passengerContact,String passengerEmailId,String passengerPhoto);
    }

}
