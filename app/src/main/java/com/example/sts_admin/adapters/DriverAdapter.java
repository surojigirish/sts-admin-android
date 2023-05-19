package com.example.sts_admin.adapters;

import android.content.Context;
import android.util.Log;
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
//    OnDriverItemClickListener onDriverItemClickListener;

    OnItemClickListener onItemClickListener;


    public DriverAdapter(List<Driver> driverList, Context context, OnItemClickListener listener) {
        this.driverList = driverList;
        this.context = context;
        this.onItemClickListener = listener;
    }

    public DriverAdapter(List<Driver> driverList, Context context) {
        this.driverList = driverList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.driver_list_item,
                        parent,
                        false);

        return new ViewHolder(view, onItemClickListener, driverList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String driverName = driverList.get(position).getFirstname() + " " +
                driverList.get(position).getLastname();

        holder.driverName.setText(driverName);
        holder.driverEmployeeNo.setText(driverList.get(position).getEmployeeNo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();

                if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                    Driver clickedDriver = driverList.get(position);
                    int id = clickedDriver.getEmpId();
                    String employeeNum = clickedDriver.getEmployeeNo();
                    String firstName = clickedDriver.getFirstname();
                    String lastName = clickedDriver.getLastname();

                    onItemClickListener.onItemClick(id, employeeNum, firstName, lastName);
                    Log.i("TAG", "Adapter onClick: " + firstName + " " + lastName);
                }
            }
        });

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION && onDriverItemClickListener != null){
                    Driver selectedDriver = driverList.get(pos);
                    Integer empId = selectedDriver.getEmpId();
                    String employeeNo = selectedDriver.getEmployeeNo();
                    String firstname = selectedDriver.getFirstname();
                    String lastname  = selectedDriver.getLastname();
                    onDriverItemClickListener.onDriverClickListener(empId, employeeNo, firstname, lastname);
                    Log.i("TAG", "Adapter onClick: " + firstname + " " + lastname);
                }
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Views
        TextView driverName, driverEmployeeNo;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener, final List<Driver> drivers) {
            super(itemView);

            // init the views
            driverName = itemView.findViewById(R.id.tv_driver_name);
            driverEmployeeNo = itemView.findViewById(R.id.tv_driver_emp_no);
        }
    }

    /*public interface OnDriverItemClickListener{
        void onDriverClickListener(Integer empId,String employeeNo,String firstname,String lastname);
    }*/

    public interface OnItemClickListener {
        void onItemClick(Integer id, String employeeNo, String firstName, String lastName);
    }
}
