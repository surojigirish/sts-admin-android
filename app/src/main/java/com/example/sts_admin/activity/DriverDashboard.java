package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sts_admin.R;

public class DriverDashboard extends AppCompatActivity {

    CardView busScheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dashboard);

        busScheduleList=findViewById(R.id.bus_schedule_list);
        busScheduleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DriverDashboard.this,BusScheduleList.class);
                startActivity(intent);
            }
        });
    }
}