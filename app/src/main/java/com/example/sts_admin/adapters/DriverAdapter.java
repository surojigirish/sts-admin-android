package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.Driver;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder> {

    List<Driver> driverList;
    Context context;
    OnClickDriverDetails onClickDriverDetails;

    public DriverAdapter(List<Driver> driverList, Context context, OnClickDriverDetails onClickDriverDetails) {
        this.driverList = driverList;
        this.context = context;
        this.onClickDriverDetails = onClickDriverDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.driver_list_item,
                        parent,
                        false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String driverName = driverList.get(position).getFirstname() + " " +
                driverList.get(position).getLastname();

        holder.driverName.setText(driverName);
        holder.driverEmployeeNo.setText(driverList.get(position).getEmployeeNo());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && onClickDriverDetails != null){
                    Driver selectedDriver = driverList.get(pos);
                    String driverFirstName = selectedDriver.getFirstname();
                    String driverLastName = selectedDriver.getLastname();
                    String driverLicenseNo = selectedDriver.getLicenseNo();
                    String driverContact = selectedDriver.getContact();

                    onClickDriverDetails.onClickItem(driverFirstName,driverLastName,driverLicenseNo,driverContact);

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Views
        TextView driverName, driverEmployeeNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // init the views
            driverName = itemView.findViewById(R.id.tv_driver_name);
            driverEmployeeNo = itemView.findViewById(R.id.tv_driver_emp_no);

        }
    }


    public interface OnClickDriverDetails {
        void onClickItem(String driverFirstName,String driverLastName,String driverLicenseNo,String driverContact);

    }

}
