package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sts_admin.R;

public class RouteInfoList extends AppCompatActivity {

    TextView routeBusType, routeDistance, routeFare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_info_list);

//        routeBusType = findViewById(R.id.routeBusType);
        routeDistance = findViewById(R.id.routeDistance);
        routeFare = findViewById(R.id.routeFare);

        Intent i = getIntent();
//        String infoRouteBusType = i.getStringExtra("infoRouteBusType");
        String infoRouteDistance = i.getStringExtra("infoRouteDistance");
        String infoRouteFare = i.getStringExtra("infoRouteFare");


//        routeBusType.setText(infoRouteBusType);
        routeDistance.setText(infoRouteDistance);
        routeFare.setText(infoRouteFare);


    }
}