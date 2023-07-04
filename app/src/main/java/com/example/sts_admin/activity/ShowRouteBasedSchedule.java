package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sts_admin.R;

public class ShowRouteBasedSchedule extends AppCompatActivity {

    TextView routeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route_based_schedule);

        routeId = findViewById(R.id.tvShowRouteId);
        String rid = String.valueOf(getRouteId());
        routeId.setText(rid);


    }

    private int getRouteId() {
        Intent intent = getIntent();
        return intent.getIntExtra("routeId", -1);
    }
}