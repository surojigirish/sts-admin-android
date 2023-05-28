package com.example.sts_admin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sts_admin.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class BusScheduleInfoListScanner extends AppCompatActivity {

    private static final int REQUEST_CHECK_SETTINGS =0 ;
    TextView driverBusId, driverScheduleId;

    Button locationEnableDisable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedule_info_list_scanner);

        driverBusId=findViewById(R.id.driver_busId);
        driverScheduleId=findViewById(R.id.driver_scheduleId);
        locationEnableDisable=findViewById(R.id.enable_disable);
//
//        locationEnableDisable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final int REQUEST_CHECK_SETTINGS = 1001;
//                GoogleApiClient googleApiClient;
//                LocationRequest locationRequest;
//
//                private void checkLocationSettings() {
//                    googleApiClient = new GoogleApiClient.Builder(BusScheduleInfoListScanner.this)
//                            .addApi(LocationServices.API)
//                            .build();
//                    googleApiClient.connect();
//
//                    locationRequest = LocationRequest.create();
//                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//
//                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                            .addLocationRequest(locationRequest);
//
//                    SettingsClient settingsClient = LocationServices.getSettingsClient(BusScheduleInfoListScanner.this);
//                    Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());
//
//                    task.addOnSuccessListener(BusScheduleInfoListScanner.this, new OnSuccessListener<LocationSettingsResponse>() {
//                        @Override
//                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
//                            // Location settings are satisfied. Start requesting location updates or perform other actions.
//                            // Location services are enabled.
//                        }
//                    });
//
//                    task.addOnFailureListener(BusScheduleInfoListScanner.this, new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            if (e instanceof ResolvableApiException) {
//                                try {
//                                    // Location settings are not satisfied, but the user can enable them.
//                                    // Show a dialog prompting the user to enable location services.
//                                    ResolvableApiException resolvable = (ResolvableApiException) e;
//                                    resolvable.startResolutionForResult(BusScheduleInfoListScanner.this, REQUEST_CHECK_SETTINGS);
//                                } catch (IntentSender.SendIntentException sendEx) {
//                                    // Ignore the error.
//                                }
//                            }
//                        }
//                    });
//                }
//
//
//            }
//
//        });





        Intent intent= getIntent();
        String busId = intent.getStringExtra("busId");
        String scheduleId= intent.getStringExtra("scheduleId");

        driverBusId.setText(busId);
        driverScheduleId.setText(scheduleId);

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

}