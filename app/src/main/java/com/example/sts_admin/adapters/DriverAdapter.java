package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.UserDriver;

import org.w3c.dom.Text;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder> {

    List<UserDriver> driverList;
    Context context;

    public DriverAdapter(Context context, List<UserDriver> driverList) {
        this.context = context;
        this.driverList = driverList;
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
}
