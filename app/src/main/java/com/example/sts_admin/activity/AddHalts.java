package com.example.sts_admin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.MapsActivity;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.HaltRequest;
import com.example.sts_admin.apiservice.response.HaltResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddHalts extends AppCompatActivity {

    EditText etHaltName, etHaltLongitude, etHaltLatitude;
    AppCompatButton btnAddHalt, btnGetLatLong;

    // location
    private FusedLocationProviderClient fusedLocationProviderClient;
    Double latitude;
    Double longitude;

    private void setHaltLocation(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @SuppressLint("SetTextI18n")
    private void getHaltLocation(){
        etHaltLongitude.setText(longitude.toString());
        etHaltLatitude.setText(latitude.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_source);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        etHaltName=findViewById(R.id.et_halt_name);
        etHaltLatitude=findViewById(R.id.et_halt_lat);
        etHaltLongitude=findViewById(R.id.et_halt_long);

        btnAddHalt = findViewById(R.id.btn_add_halt);
        btnGetLatLong = findViewById(R.id.btn_geo_location);

    }

    @Override
    protected void onStart() {
        super.onStart();

        requestLocationPermission();

        btnAddHalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                source(haltRequest());
                Toast.makeText(AddHalts.this, "Halt added successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AddHalts.this,UpdateSchedule.class);
                startActivity(i);
                finish();
            }
        });

        btnGetLatLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHaltLocation();
            }
        });
    }

    public void requestLocationPermission() {
        Log.i("TAG", "requestLocationPermission: ");
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    AddHalts.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    Consts.LOCATION_REQUEST_CODE
            );

            Log.i("TAG", "requestLocationPermission: if-block called");
        } else {
            getCurrentLocation();
            Log.i("TAG", "requestLocationPermission: else");
        }
    }

    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted
            Log.i("TAG", "getCurrentLocation: permission not granted");
            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Log.i("TAG", "onSuccess: " +location.getLongitude() + " " +location.getLatitude());
                            setHaltLocation(location.getLongitude(), location.getLatitude());
                            // Do something with the current location
                        } else {
                            Toast.makeText(getApplicationContext(), "Unable to get location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.i("TAG", "onRequestPermissionsResult: " + requestCode);
        if (requestCode == Consts.LOCATION_REQUEST_CODE && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted
            getCurrentLocation();
        } else {
            Toast.makeText(getParent(), "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }


    public HaltRequest haltRequest(){
        HaltRequest haltRequest = new HaltRequest();
        haltRequest.setName(etHaltName.getText().toString());
        haltRequest.setLatitude(etHaltLatitude.getText().toString());
        haltRequest.setLongitude(etHaltLongitude.getText().toString());
        return haltRequest;
    }

    public void source(HaltRequest haltRequest){

        Call<HaltResponse> sourceResponseCall= Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().addHalts(haltRequest);
        sourceResponseCall.enqueue(new Callback<HaltResponse>() {
            @Override
            public void onResponse(Call<HaltResponse> call, Response<HaltResponse> response) {
                HaltResponse sourceResponse=response.body();
                if (response.body() != null && response.isSuccessful() && response.body().getStatus() == 200) {
//                    Toast.makeText(AddHalts.this, "Added", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<HaltResponse> call, Throwable t) {

            }
        });

    }
}