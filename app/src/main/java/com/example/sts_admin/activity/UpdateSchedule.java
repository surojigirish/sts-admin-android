package com.example.sts_admin.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sts_admin.R;
import com.example.sts_admin.model.RouteInfo;

public class UpdateSchedule extends AppCompatActivity {

    AppCompatButton btnAddRoute, btnAddSource, btnRouteInfo, btnScheduleInfo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_schedule);

        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnAddRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UpdateSchedule.this, AddRoute.class);
                startActivity(intent);
            }
        });

        btnAddSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UpdateSchedule.this, AddHalts.class);
                startActivity(intent);
            }
        });

        btnRouteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UpdateSchedule.this, RouteInfoActivity.class);
                startActivity(intent);
            }
        });

        btnScheduleInfo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UpdateSchedule.this, AddSchedule.class);
                startActivity(intent);
            }
        });
    }


    // initialize views
    public void initViews() {
        btnAddRoute =findViewById(R.id.add_route);
        btnAddSource =findViewById(R.id.add_source);
        btnRouteInfo = findViewById(R.id.btn_route_info);
        btnScheduleInfo = findViewById(R.id.btn_schedule_info);
    }

    public void hideViewsOnFragTransaction() {
        // Hide the views in the current activity
        btnRouteInfo.setVisibility(View.GONE);
        btnScheduleInfo.setVisibility(View.GONE);
        btnAddRoute.setVisibility(View.GONE);
        btnAddSource.setVisibility(View.GONE);
    }
}