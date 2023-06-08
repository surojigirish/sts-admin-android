package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.Passenger;
import com.example.sts_admin.model.results.TicketResult;

import java.util.List;

public class PassengerDaetailsAdapter extends RecyclerView.Adapter<PassengerDaetailsAdapter.ViewHolder> {

    List<TicketResult> ticketResultList;
    Context context;

    public PassengerDaetailsAdapter(List<TicketResult> ticketResultList, Context context) {
        this.ticketResultList = ticketResultList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.onboardpassenger_itemlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String id = String.valueOf(ticketResultList.get(position).getPassenger().getPassengerId());
        holder.passengerFname.setText(ticketResultList.get(position).getPassenger().getFirstName());
        holder.PassengerLname.setText(ticketResultList.get(position).getPassenger().getLastName());
        holder.pGender.setText(ticketResultList.get(position).getPassenger().getGender());
        holder.pCategory.setText(ticketResultList.get(position).getPassenger().getCategory());
        holder.bookedAt.setText(ticketResultList.get(position).getTicket().getBooked_at());
        holder.source.setText(ticketResultList.get(position).getTicket().getSource());
        holder.destination.setText(ticketResultList.get(position).getTicket().getDestination());
        holder.distanceTravelled.setText(ticketResultList.get(position).getTicket().getDistanceTravelled());
        holder.tickedStatus.setText(ticketResultList.get(position).getTicket().getStatus());
    }

    @Override
    public int getItemCount() {
        return ticketResultList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView passengerFname,PassengerLname,pGender,pCategory,bookedAt,source,destination,distanceTravelled,tickedStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            passengerFname = itemView.findViewById(R.id.passengerFname);
            PassengerLname = itemView.findViewById(R.id.passengerLname);
            pGender = itemView.findViewById(R.id.passengerGender);
            pCategory = itemView.findViewById(R.id.passengerCategory);
            bookedAt = itemView.findViewById(R.id.ticketBookedAt);
            source = itemView.findViewById(R.id.ticketSource);
            destination = itemView.findViewById(R.id.ticketDest);
            distanceTravelled = itemView.findViewById(R.id.ticketDistance);
            tickedStatus = itemView.findViewById(R.id.ticketStatus);
        }
    }
}
