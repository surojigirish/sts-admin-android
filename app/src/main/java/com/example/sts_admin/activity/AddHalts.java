package com.example.sts_admin.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddHalts extends AppCompatActivity {

    EditText etHaltName, etHaltLongitude, etHaltLatitude;
    AppCompatButton btnAddHalt, btnGetLatLong, btnGoogleMaps;

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
        btnGoogleMaps = findViewById(R.id.btn_googleMaps);

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

        btnGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLocationFromMap();
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

    // Method to launch Google Maps and select a location
    private void selectLocationFromMap() {
        // Create a Uri to specify the map mode and marker position
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo")
                .appendQueryParameter("q", "15.49606757343556, 73.83635705315176") // Default marker position
                .appendQueryParameter("z", "15");  // Zoom level
        Uri uri = builder.build();


        // Intent to launch Google Maps
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        mapIntent.setPackage("com.google.android.apps.maps");

        /*// Specify the map mode to display
        mapIntent.putExtra("map_mode", "place");*/

        // Start the activity and wait for the result
        startActivityForResult(mapIntent, Consts.REQUEST_CODE_MAP_LOCATION);
    }

    // Handle the result of the Google Maps activity

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Consts.REQUEST_CODE_MAP_LOCATION) {
            if (resultCode == RESULT_OK) {
                // Get the selected location coordinates from the result intent
                if (data != null && data.getData() != null) {
                    Uri uri = data.getData();

                    double latitude = Double.parseDouble(uri.getQueryParameter("latitude"));
                    double longitude = Double.parseDouble(uri.getQueryParameter("longitude"));
                    /*double latitude = data.getDoubleExtra("latitude", 0.0);
                    double longitude = data.getDoubleExtra("longitude", 0.0);*/

                    // Use the lat && long in app


                    // Display a toast message with selected coordinates
                    Toast.makeText(this, "Selected location " + latitude + ", " + longitude, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}