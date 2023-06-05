package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.RouteRequest;
import com.example.sts_admin.apiservice.response.RouteResponse;
import com.example.sts_admin.fragments.RouteSourceFragment;
import com.example.sts_admin.fragments.SourceSearchFragment;
import com.example.sts_admin.model.Route;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRoute extends AppCompatActivity {

    EditText source,destination;
    ConstraintLayout constraintDesign;
    AppCompatButton addNewRoute;

    // SharedPreferences to Handle route data
    SharedPreferences sf;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        initializeViews();

        source=findViewById(R.id.source);
        destination=findViewById(R.id.destination);
        addNewRoute=findViewById(R.id.add_route_btn);

        getSharedPrefData();

        // Source onClick handler to select Source Halt from available list
        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if the fragment is already added
                RouteSourceFragment routeSourceFragment = new RouteSourceFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout_route_container, routeSourceFragment);
                transaction.commit();

                // hide views on call
                hideViewsOnFrag();
            }
        });

        // OnClick handler to add route on button click
        addNewRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                route(routeRequest());
                Toast.makeText(AddRoute.this, "Route added", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AddRoute.this, UpdateSchedule.class);
                startActivity(i);
                finish();
            }
        });

    }


    public RouteRequest routeRequest() {
        RouteRequest addRequest=new RouteRequest();

        addRequest.setSource(source.getText().toString());
        addRequest.setDestination(destination.getText().toString());
        return addRequest;

    }

    public void route(RouteRequest routeRequest){
        Call<RouteResponse> addResponseCall= Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().addRoute(routeRequest);
        addResponseCall.enqueue(new Callback<RouteResponse>() {
            @Override
            public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "onResponse: success");
                } else {
                    Log.i("ADD ROUTE DATA", "onResponse: unable to add route data");
                }
            }

            @Override
            public void onFailure(Call<RouteResponse> call, Throwable t) {

            }
        });
    }

    private void getSharedPrefData() {
        int sourceId = sf.getInt("sourceId", 0);
        String sourceName = sf.getString("sourceName", "");
        int destinationId = sf.getInt("destinationId", 0);
        String destinationName = sf.getString("destinationName", "");

        if (sourceName.isEmpty() && destinationName.isEmpty()) {
            sourceName = "SOURCE";
            destinationName = "DESTINATION";
        }

        if (sourceName.isEmpty()) {
            sourceName = "SOURCE";
        }

        if (destinationName.isEmpty()) {
            destinationName = "DESTINATION";
        }

        source.setText(sourceName);
        destination.setText(destinationName);
    }

    private void initializeViews() {
        // SharedPreferences
        sf = getSharedPreferences("route-data", Context.MODE_PRIVATE);

        // Views
        constraintDesign = findViewById(R.id.constraint_design);
    }


    // Hide views on fragment call
    private void hideViewsOnFrag() {

        // Constraint Design hide
        constraintDesign.setVisibility(View.INVISIBLE);

        // EditTextView
        source.setVisibility(View.INVISIBLE);
        destination.setVisibility(View.INVISIBLE);
        addNewRoute.setVisibility(View.INVISIBLE);
    }
}