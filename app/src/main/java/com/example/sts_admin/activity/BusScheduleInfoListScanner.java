package com.example.sts_admin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.LocationUpdate;
import com.example.sts_admin.services.LocationUpdateService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusScheduleInfoListScanner extends AppCompatActivity {

    private static final int REQUEST_CHECK_SETTINGS = 0;
    TextView tvBusScheduleID;

    Button locationEnableDisable;

    // Location access
    // Declare FusedLocationProviderClient as a class variable
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationUpdate locationUpdateRequest;
    private PendingIntent locationUpdatePendingIntent;
    private boolean sendingLocationEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedule_info_list_scanner);

        tvBusScheduleID = findViewById(R.id.tv_driverBusSchedule);
        locationEnableDisable = findViewById(R.id.enable_disable);


        // set data to views
        Intent intent = getIntent();
        String busScheduleId = intent.getStringExtra("busScheduleId");

        tvBusScheduleID.setText(busScheduleId);


        initFusedLocationProviderClient();

        locationEnableDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sendingLocationEnabled) {
                    stopSendingLocation();
                } else {
                    startSendingLocation();
                }
                /*requestUserLocation();*/
            }
        });

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
        }
    }

    // Stop sending location updates
    private void stopSendingLocation() {
        if (sendingLocationEnabled) {
            sendingLocationEnabled = false;
            locationEnableDisable.setText("Enable Sending Location");

            // Stop location updates
            fusedLocationProviderClient.removeLocationUpdates(locationUpdatePendingIntent);
        }
    }

    // Location update request for API call
    private LocationUpdate createLocationUpdateRequest(double lat, double lng) {
        LocationUpdate request = new LocationUpdate();

        request.setLat(lat);
        request.setLng(lng);

        return request;
    }

    // Location update api call
    private void updateLocation(LocationUpdate request) {
        // update counter
        counter++;
        Log.i("TAG", "updateLocation: counter incremented on call" + counter);

        Call<Void> call = Client.getInstance(Consts.BASE_URL_LOCATION)
                .getRoute().updateLocation(21, request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "onResponse: Location updated");
                    Toast.makeText(BusScheduleInfoListScanner.this, "Location updated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("TAG", "onFailure: Error updating location", t);
                Toast.makeText(BusScheduleInfoListScanner.this, "Error updating location", Toast.LENGTH_SHORT).show();
            }
        });


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
        locationUpdatePendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Background service for location
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationUpdatePendingIntent);


        /*// fused location provider client
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult != null) {
                    Location location = locationResult.getLastLocation();
                    if (location != null) {
                        // get location data
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        // log location
                        Log.i("TAG", "onSuccess: Location updated lat: " + latitude + " long: " + longitude);

                        // create location update request object
                        LocationUpdate request = createLocationUpdateRequest(latitude, longitude);

                        // make api call
                        updateLocation(request);
                    }
                }
            }
        }, null);*/
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

    private int counter = 0;

}