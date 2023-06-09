package com.example.sts_admin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.PassengerDaetailsAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.LocationUpdate;
import com.example.sts_admin.apiservice.response.OnBoardPassengerResponse;
import com.example.sts_admin.model.results.TicketResult;
import com.example.sts_admin.services.LocationUpdateService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusScheduleLocation extends AppCompatActivity {


    List<TicketResult> ticketResultList;

    private static final int REQUEST_CHECK_SETTINGS = 0;
    TextView tvBusScheduleID;
    int busScheduleId;

    AppCompatButton locationEnableDisable;

    // Location access
    // Declare FusedLocationProviderClient as a class variable
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationUpdate locationUpdateRequest;
    private PendingIntent locationUpdatePendingIntent;
    private boolean sendingLocationEnabled = false;
    RecyclerView recyclerViewPassenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedule_location);

        tvBusScheduleID = findViewById(R.id.tv_driverBusSchedule);
        locationEnableDisable = findViewById(R.id.appCompatButton_location);
        recyclerViewPassenger = findViewById(R.id.showOnboardPassengerrecyclerView);
        recyclerViewPassenger.setHasFixedSize(true);
        recyclerViewPassenger.setLayoutManager(new LinearLayoutManager(getApplication()));


        // get intent data from previous activity
        Intent intent = getIntent();
        busScheduleId = intent.getIntExtra("busScheduleId", 0);

        tvBusScheduleID.setText(String.valueOf(busScheduleId));




        initFusedLocationProviderClient();

        locationEnableDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sendingLocationEnabled) {
                    stopSendingLocation();
                } else {
                    startSendingLocation();
                }
            }
        });

        getOnBoardPassengerDeatils(busScheduleId);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == RESULT_OK) {
                // User enabled location services.
            } else {
                // User did not enable location services.
            }
        }
    }


    // Initialize fused location
    private void initFusedLocationProviderClient() {
        // In your activity's onCreate() or a suitable initialization method
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    // Start sending location updates
    private void startSendingLocation() {
        if (!sendingLocationEnabled) {
            sendingLocationEnabled = true;
            locationEnableDisable.setText("Disable Sending Location");

            // Request user location
            requestUserLocation();

            // Start LocationUpdateService
            Intent serviceIntent = new Intent(this, LocationUpdateService.class);
            serviceIntent.putExtra("busScheduleId", busScheduleId);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent);
                Log.i("TAG", "startSendingLocation: startForegroundService " );
            } else {
                startService(serviceIntent);
                Log.i("TAG", "startSendingLocation: startService " );
            }
        }
    }

    // Stop sending location updates
    private void stopSendingLocation() {
        if (sendingLocationEnabled) {
            sendingLocationEnabled = false;
            locationEnableDisable.setText("Enable Sending Location");

            // Stop location updates
            fusedLocationProviderClient.removeLocationUpdates(locationUpdatePendingIntent);

            // Stop LocationUpdateService
            Intent serviceIntent = new Intent(this, LocationUpdateService.class);
            stopService(serviceIntent);
        }
    }


    // User current location request
    private void requestUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, Consts.LOCATION_REQUEST_CODE);
            return;
        }

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);   // update location every 10s
        locationRequest.setFastestInterval(5000);    // use fastest interval
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Create a PendingIntent for the location update
        Intent intent = new Intent(this, LocationUpdateService.class);
        intent.putExtra("busScheduleId", busScheduleId);
        locationUpdatePendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_MUTABLE);

        // Background service for location
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationUpdatePendingIntent);
    }

    // location request permission check
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Consts.LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted, proceed with location retrieval
                requestUserLocation();
            } else {
                // Location permission not granted, handle accordingly (e.g., show an error message)
                Log.i("TAG", "onRequestPermissionsResult: Location request not granted");
            }
        }
    }



    /*----------------------------------- get Onboard passenger details call-------------------------------------*/

    public void getOnBoardPassengerDeatils(int busScheduleId){
        Call<OnBoardPassengerResponse> call = Client.getInstance(Consts.BASE_URL_BOOKING).getRoute().getPassengerDeatails(busScheduleId);
        call.enqueue(new Callback<OnBoardPassengerResponse>() {
            @Override
            public void onResponse(Call<OnBoardPassengerResponse> call, Response<OnBoardPassengerResponse> response) {
                if (response.isSuccessful()){
                    ticketResultList = response.body().getTicketResultList();
                    recyclerViewPassenger.setAdapter(new PassengerDaetailsAdapter(ticketResultList, getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<OnBoardPassengerResponse> call, Throwable t) {

            }
        });

    }
}