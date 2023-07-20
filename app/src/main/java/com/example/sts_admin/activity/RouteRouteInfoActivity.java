package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

    AppCompatImageView no_schedule_data_image;


    // Routes Info list
    List<RouteInfoResult> routeInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_route_info);

        initializeViews();
        routeRouteInfo();

        fabNewRouteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RouteRouteInfoActivity.this, RouteInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    // Initialize views
    private void initializeViews() {
        // TextViews
        tvRouteSource = findViewById(R.id.tv_route_routeInfo_source);
        tvRouteDestination = findViewById(R.id.tv_route_routeInfo_destination);
        no_schedule_data_image = findViewById(R.id.no_data);
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

                        // change ui based on data
                        if (routeInfoList.isEmpty()){
                            rvRoutesInfo.setVisibility(View.GONE);
                            tvRouteSource.setVisibility(View.GONE);
                            tvRouteDestination.setVisibility(View.GONE);
                            no_schedule_data_image.setVisibility(View.VISIBLE);

                            // Use Glide to load the image into the ImageView
                            Glide.with(getApplicationContext())
                                    .load(R.drawable.no_results)
                                    .into(no_schedule_data_image);
                        }else {
                            no_schedule_data_image.setVisibility(View.GONE);
                            // Set the adapter
                            rvRoutesInfo.setAdapter(new ExtendedRouteInfoAdapter(routeInfoList));

                        }

                        }
                }
            }

            @Override
            public void onFailure(Call<RouteRoutesInfoResponse> call, Throwable t) {

            }
        });
    }
}