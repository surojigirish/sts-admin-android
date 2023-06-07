package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sts_admin.R;

public class BusDetailsList extends AppCompatActivity {

    TextView busDetailsId, busDetailsCapacity, busDetailsStatus, busDetailsType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details_list);
//
        busDetailsId = findViewById(R.id.tv_bus_id);
        busDetailsCapacity= findViewById(R.id.tv_bus_capacity);
        busDetailsStatus = findViewById(R.id.tv_bus_status);
        busDetailsType= findViewById(R.id.tv_bus_details_type);

        Intent i = getIntent();
        Integer busId = i.getIntExtra("busId",0);
        Integer busCapacity = i.getIntExtra("busCapacity",0);
        String busStatus = i.getStringExtra("busStatus");
        String busType = i.getStringExtra("busType");


        busDetailsCapacity.setText(busCapacity.toString());
        busDetailsId.setText(busId.toString());
        busDetailsStatus.setText(busStatus);
        busDetailsType.setText(busType);
    }
}