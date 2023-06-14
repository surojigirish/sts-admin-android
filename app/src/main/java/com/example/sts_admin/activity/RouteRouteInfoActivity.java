package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.ExtendedRouteInfoAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.RouteRoutesInfoResponse;
import com.example.sts_admin.model.RouteInfoResult;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteRouteInfoActivity extends AppCompatActivity {

    // TextViews
    TextView tvRouteSource, tvRouteDestination;

    // RecyclerView
    RecyclerView rvRoutesInfo;

    // FloatingActionButton
    FloatingActionButton fabNewRouteInfo;

    // Routes Info list
    List<RouteInfoResult> routeInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_route_info);

        initializeViews();
        routeRouteInfo();
    }

    // Initialize views
    private void initializeViews() {
        // TextViews
        tvRouteSource = findViewById(R.id.tv_route_routeInfo_source);
        tvRouteDestination = findViewById(R.id.tv_route_routeInfo_destination);

        // RecyclerView
        rvRoutesInfo = findViewById(R.id.recyclerView_route_routeInfo);
        rvRoutesInfo.setHasFixedSize(true);
        rvRoutesInfo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // FloatingActionButton
        fabNewRouteInfo = findViewById(R.id.floatingActionButton_add_routeInfo);
    }

    private int getRouteId() {
        Intent intent = getIntent();
        return intent.getIntExtra("routeId", -1);
    }

    private void routeRouteInfo() {
        // Get route id from intent
        int routeId = getRouteId();

        // API call to get routes info list
        Call<RouteRoutesInfoResponse> call = Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().routeRoutesInfo(routeId);
        call.enqueue(new Callback<RouteRoutesInfoResponse>() {
            @Override
            public void onResponse(Call<RouteRoutesInfoResponse> call, Response<RouteRoutesInfoResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        // route data
                        String routeSource = response.body().getRoute().getSource();
                        String routeDestination = response.body().getRoute().getDestination();
                        tvRouteSource.setText(routeSource);
                        tvRouteDestination.setText(routeDestination);

                        // RouteInfo results in the list
                        routeInfoList = response.body().getRouteInfoResults();

                        // Set the adapter
                        rvRoutesInfo.setAdapter(new ExtendedRouteInfoAdapter(routeInfoList));
                    }
                }
            }

            @Override
            public void onFailure(Call<RouteRoutesInfoResponse> call, Throwable t) {

            }
        });
    }
}