package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.GetRouteDetailsAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.RouteRequest;
import com.example.sts_admin.apiservice.response.GetRouteResponse;
import com.example.sts_admin.apiservice.response.RouteResponse;
import com.example.sts_admin.fragments.RouteDestinationFragment;
import com.example.sts_admin.fragments.RouteSourceFragment;
import com.example.sts_admin.model.AddRouteDetails;
import com.example.sts_admin.model.Halts;
import com.example.sts_admin.model.Route;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRoute extends AppCompatActivity {

    EditText source,destination;
    ConstraintLayout constraintDesign;
    AppCompatButton addNewRoute;

    // SharedPreferences to Handle route data
    SharedPreferences sf;
    SharedPreferences.Editor editor;

    // Store source halt
    Halts sourceBusStand;
    // Store destination halt
    Halts destinationBusStand;

    // RecyclerView to show route data
    RecyclerView recyclerView;
    List<AddRouteDetails> addRouteDetailsList;
    List<Route> routeList;

    // Refresh Route list
    private SwipeRefreshLayout swipeRefreshLayout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        initializeViews();

        source=findViewById(R.id.source);
        destination=findViewById(R.id.destination);
        addNewRoute=findViewById(R.id.add_route_btn);

        getAllRouteList();

        // Get sharedpref data
        getSharedPrefData();
        // set views data
        setViewData();

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

        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the fragment is already added
                RouteDestinationFragment fragment = new RouteDestinationFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout_route_container, fragment);
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
                clearRouteSharedPref();
                resetViews();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllRouteList();
            }
        });

    }



    public RouteRequest routeRequest() {
        RouteRequest request=new RouteRequest();

        // get data from sharedpref
        int sourceId = sourceBusStand.getId();
        int destinationId = destinationBusStand.getId();

        request.setSourceId(sourceId);
        request.setDestinationId(destinationId);

        return request;

    }

    public void route(RouteRequest routeRequest){
        Call<RouteResponse> addResponseCall= Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().addRoute(routeRequest);
        addResponseCall.enqueue(new Callback<RouteResponse>() {
            @Override
            public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        int status = response.body().getStatus();
                        String message = response.body().getMessage();

                        if (status == 200) {
                            Log.i("TAG", "onResponse: success");
                            Toast.makeText(AddRoute.this, message, Toast.LENGTH_SHORT).show();
                            // Start Schedule Dashboard activity
                            Intent i = new Intent(AddRoute.this, UpdateSchedule.class);
                            startActivity(i);
                            finish();
                        } else if (status == 405) {
                            Log.i("TAG", "onResponse: failed");
//                            Toast.makeText(AddRoute.this, "Already exists", Toast.LENGTH_SHORT).show();
                            Snackbar.make(addNewRoute, message, Snackbar.LENGTH_SHORT).show();
                        } else {
                            Log.i("TAG", "onResponse: unknown status");
                            Toast.makeText(AddRoute.this, "Unknown status", Toast.LENGTH_SHORT).show();
                        }
                    }
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
        // init the source and destination halts
        sourceBusStand = new Halts();
        destinationBusStand = new Halts();

        sourceBusStand.setId(sf.getInt("sourceId", 0));
        sourceBusStand.setName(sf.getString("sourceName", ""));
        destinationBusStand.setId(sf.getInt("destinationId", 0));
        destinationBusStand.setName(sf.getString("destinationName", ""));
    }

    private void setViewData() {
        String sourceName = sourceBusStand.getName();
        String destinationName = destinationBusStand.getName();

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

        // Recycler View
        recyclerView = findViewById(R.id.recyclerViewRouteDetails);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // Swipe to refresh
        swipeRefreshLayout = findViewById(R.id.swipeToRefreshRoutes);
    }

    private void getAllRouteList(){
        Call<GetRouteResponse> getRouteResponseCall = Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().getAllRouteList();

        getRouteResponseCall.enqueue(new Callback<GetRouteResponse>() {
            @Override
            public void onResponse(Call<GetRouteResponse> call, Response<GetRouteResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        addRouteDetailsList = response.body().getAddRouteDetailsList();
                        recyclerView.setAdapter(new GetRouteDetailsAdapter(addRouteDetailsList,getApplicationContext()));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetRouteResponse> call, Throwable t) {

            }
        });

        // Notify the SwipeRefreshLayout that the refresh action has finished
        swipeRefreshLayout.setRefreshing(false);
    }


    // Hide views on fragment call
    private void hideViewsOnFrag() {

        // Constraint Design hide
        constraintDesign.setVisibility(View.INVISIBLE);

        // EditTextView
        source.setVisibility(View.INVISIBLE);
        destination.setVisibility(View.INVISIBLE);
        addNewRoute.setVisibility(View.INVISIBLE);

        // Swipe to refresh
        swipeRefreshLayout.setVisibility(View.INVISIBLE);
    }

    // Clear Route SharedPref
    public void clearRouteSharedPref() {
        editor = sf.edit();
        editor.clear();
        editor.apply();
    }

    // Reset textview and editTextView
    public void resetViews() {
        source.setText(Consts.SOURCE);
        destination.setText(Consts.DESTINATION);
    }
}