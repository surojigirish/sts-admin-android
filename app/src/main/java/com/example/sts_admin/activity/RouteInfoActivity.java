package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.RouteInfoRequest;
import com.example.sts_admin.apiservice.response.RouteInfoResponse;
import com.example.sts_admin.fragments.DestinationSearchFragment;
import com.example.sts_admin.fragments.RouteSearchFragment;
import com.example.sts_admin.fragments.SourceSearchFragment;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteInfoActivity extends AppCompatActivity {


    // views
    TextView tvDistance, tvFare;
    TextView etRouteInfo, etSource, etDestination;
    EditText fareAmount;
    AppCompatButton btnAddRouteInfo,routeInfoDetailsBtn;
    AppCompatImageButton backButton;
    Spinner busTypeSpinner;
    String busTypeItem,finalDistance,finalFare;
    Double BASE_FARE;
    LinearLayout llRouteInfoViewHolder;

    // get data from shared pref manager
    SharedPreferences sharedPreferences;

    // on activity create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_info);

        // init views
        initializeViews();

        // init sharedpref
        sharedPreferences = getSharedPreferences("route", Context.MODE_PRIVATE);
    }

    // on activity start
    @SuppressLint("DefaultLocale")
    @Override
    protected void onStart() {
        super.onStart();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateSchedule.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        int routeId = sharedPreferences.getInt("routeId", 0);
        String routeDestination = sharedPreferences.getString("routeDestination", "");
        String routeSource = sharedPreferences.getString("routeSource", "");

        // source sp
        int sourceId = sharedPreferences.getInt("sourceId", 0);
        String sourceName = sharedPreferences.getString("sourceName", "");
        String sourceLat = sharedPreferences.getString("sourceLat", "");
        String sourceLong = sharedPreferences.getString("sourceLong", "");

        // source sp
        int destinationId = sharedPreferences.getInt("destinationId", 0);
        String destinationName = sharedPreferences.getString("destinationName", "");
        String destinationLat = sharedPreferences.getString("destinationLat", "");
        String destinationLong = sharedPreferences.getString("destinationLong", "");


        // check if route values are present in sp
        if (routeId == 0 && routeDestination.isEmpty() && routeSource.isEmpty()) {
            // set default values
            routeSource = "N/A";
            routeDestination = "N/A";
        }

        // check if source values are present in sp
        if (sourceName.isEmpty()) {
            // set default values
            sourceName = getString(R.string.source_id);
        }

        // check if source values are present in sp
        if (destinationName.isEmpty()) {
            // set default values
            destinationName = getString(R.string.destination_id);
        }

        // set values to views
        if (Objects.equals(routeSource, "N/A") && Objects.equals(routeDestination, "N/A")) {
            etRouteInfo.setText(R.string.stands_details);
        } else {
            String routeInfo = routeSource + " " + routeDestination;
            etRouteInfo.setText(routeInfo);
        }
        etSource.setText(sourceName);
        etDestination.setText(destinationName);

        // stand details for route made clickable
        etRouteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteSearchFragment fragment = new RouteSearchFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout_route_info, fragment);
                transaction.commit();

                // hide views
                hideViewsOnFragCall();
            }
        });

        // source details
        etSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SourceSearchFragment fragment = new SourceSearchFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout_route_info, fragment);
                transaction.commit();

                // hide views
                hideViewsOnFragCall();
            }
        });

        etDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DestinationSearchFragment fragment = new DestinationSearchFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout_route_info, fragment);
                transaction.commit();

                // hide views
                hideViewsOnFragCall();
            }
        });

        busType();
        busTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                setBusTypeItem(adapterView.getItemAtPosition(position).toString());
                Log.i("TAG", "onItemSelected: selected item "+adapterView.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle the case when no item is selected
            }
        });

//        setBASE_FARE(Double.valueOf(fareAmount.getText().toString()));
        Log.i("TAG", "onStart: fare amount "+fareAmount.getText().toString());


        tvDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double doubleSourceLat = 0.0;
                double doubleSourceLong = 0.0;
                double doubleDestinationLat = 0.0;
                double doubleDestinationLong = 0.0;
                String distance = "";
                String fare = "";


                if (fareAmount.getText().toString().isEmpty()){
                    fareAmount.setError("Required");
                }


                if (!sourceLat.isEmpty() && !sourceLong.isEmpty() && !destinationLat.isEmpty() && !destinationLong.isEmpty()) {
                    try {
                        // Parse source and destination to Double
                        doubleSourceLat = Double.parseDouble(sourceLat);
                        Log.i("TAG", "onStart: source lat" + doubleSourceLat);
                        doubleSourceLong = Double.parseDouble(sourceLong);
                        doubleDestinationLat = Double.parseDouble(destinationLat);
                        doubleDestinationLong = Double.parseDouble(destinationLong);

                        // calculate distance between source and destination
                        double calculateDistance = calculateDistance(doubleSourceLat, doubleSourceLong, doubleDestinationLat, doubleDestinationLong);
                        distance = String.format("%.2f", calculateDistance);
                        tvDistance.setText(distance);

                        // calculate fare
                        double calculateFare = calculateFare(calculateDistance, Double.valueOf(fareAmount.getText().toString()));
                        fare = String.format("%.2f", calculateFare);
                        tvFare.setText(fare);
                    } catch (NumberFormatException e) {
                        // Handle parsing error
                        e.printStackTrace();

                        // show error message or handle empty
                        tvDistance.setText(R.string.distance);
                    }
                } else {
                    // handle empty lat or long
                    tvDistance.setText(R.string.distance);
                }


                // on Click call api request
                setFinalDistance(distance);
                setFinalFare(fare);

            }
        });

        fareAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double doubleSourceLat = 0.0;
                double doubleSourceLong = 0.0;
                double doubleDestinationLat = 0.0;
                double doubleDestinationLong = 0.0;
                String distance = "";
                String fare = "";

                    fareAmount.setError(null);



                if (!sourceLat.isEmpty() && !sourceLong.isEmpty() && !destinationLat.isEmpty() && !destinationLong.isEmpty()) {
                    try {
                        // Parse source and destination to Double
                        doubleSourceLat = Double.parseDouble(sourceLat);
                        Log.i("TAG", "onStart: source lat" + doubleSourceLat);
                        doubleSourceLong = Double.parseDouble(sourceLong);
                        doubleDestinationLat = Double.parseDouble(destinationLat);
                        doubleDestinationLong = Double.parseDouble(destinationLong);

                        // calculate distance between source and destination
                        double calculateDistance = calculateDistance(doubleSourceLat, doubleSourceLong, doubleDestinationLat, doubleDestinationLong);
                        distance = String.format("%.2f", calculateDistance);
                        tvDistance.setText(distance);

                        // calculate fare
                        double calculateFare = calculateFare(calculateDistance, Double.valueOf(fareAmount.getText().toString()));
                        fare = String.format("%.2f", calculateFare);
                        tvFare.setText(fare);
                    } catch (NumberFormatException e) {
                        // Handle parsing error
                        e.printStackTrace();

                        // show error message or handle empty
                        tvDistance.setText(R.string.distance);
                    }
                } else {
                    // handle empty lat or long
                    tvDistance.setText(R.string.distance);
                }


                // on Click call api request
                setFinalDistance(distance);
                setFinalFare(fare);
            }
        });




        btnAddRouteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rInfo = etRouteInfo.getText().toString();
                String src = etSource.getText().toString();
                String dest = etDestination.getText().toString();

                if (rInfo.isEmpty()){
                    etRouteInfo.setError("Select route");
                } else if (src.isEmpty()) {
                    etRouteInfo.setError(null);
                    etSource.setError("Select source");
                } else if (dest.isEmpty()) {
                    etSource.setError(null);
                    etDestination.setError("Select destination");
                }else{
                    addRouteInfo(routeInfoRequest(routeId, sourceId, destinationId, getFinalDistance(), getFinalFare()));
                }



                resetViews();
                clearRouteSharedPref();
            }
        });

        routeInfoDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RouteInfoActivity.this,RouteInfoDetails.class);
                startActivity(i);
                finish();
            }
        });

    }

    // views init function
    public void initializeViews() {

        // edittext
        etRouteInfo = findViewById(R.id.tv_route_stands);
        etSource = findViewById(R.id.tv_source);
        etDestination = findViewById(R.id.tv_destination);
        backButton = findViewById(R.id.back_btn_add_routeinfo_screen);


        // textview
        tvDistance = findViewById(R.id.tv_distance);
        tvFare = findViewById(R.id.tv_fare);
        fareAmount = findViewById(R.id.et_set_fare);

        // button
        btnAddRouteInfo = findViewById(R.id.btn_add_route_info);

        // spinner
        busTypeSpinner = findViewById(R.id.bus_type_spinner);

        // linearlayout
        llRouteInfoViewHolder = findViewById(R.id.linearlayout_route_info_view_holder);

        routeInfoDetailsBtn = findViewById(R.id.routeInfoDetailsBtn);
    }

    // hide views on fragment call
    public void hideViewsOnFragCall() {
        llRouteInfoViewHolder.setVisibility(View.GONE);
    }


    private double calculateDistance(double sourceLat, double sourceLng, double destinationLat, double destinationLng) {

        final double EARTH_RADIUS = 6371; // Earth's radius in kilometers
        double latDistance = Math.toRadians(destinationLat - sourceLat);
        double lngDistance = Math.toRadians(destinationLng - sourceLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(sourceLat)) * Math.cos(Math.toRadians(destinationLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public double calculateFare(Double distance, Double baseFare) {
        double BASE_FARE = baseFare;
        return BASE_FARE * distance;
    }


    // add route request
    private RouteInfoRequest routeInfoRequest(Integer routeId, Integer sourceId, Integer destinationId, String distance, String fare) {
        RouteInfoRequest request = new RouteInfoRequest();
        request.setRouteId(routeId);
        request.setSourceId(sourceId);
        request.setDestinationId(destinationId);
        request.setBusType(getBusTypeItem());
        request.setDistance(distance);
        request.setFare(fare);
        return request;
    }

    private void addRouteInfo(RouteInfoRequest routeInfoRequest) {
        Call<RouteInfoResponse> call = Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().addRouteInfo(routeInfoRequest);
        call.enqueue(new Callback<RouteInfoResponse>() {
            @Override
            public void onResponse(Call<RouteInfoResponse> call, Response<RouteInfoResponse> response) {
                RouteInfoResponse routeInfoResponse = response.body();
                if (response.isSuccessful() && response.body() != null && routeInfoResponse != null) {
                    Log.i("TAG", "onResponse: " +routeInfoResponse.getMessage());
                    Toast.makeText(RouteInfoActivity.this, routeInfoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("TAG", "onResponse: already exists");
                }
            }

            @Override
            public void onFailure(Call<RouteInfoResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: " +t.getLocalizedMessage());
            }
        });
    }

//    private void hideViewsOnFrag() {
//
//        // EditTextView
//        source.setVisibility(View.INVISIBLE);
//        destination.setVisibility(View.INVISIBLE);
//        addNewRoute.setVisibility(View.INVISIBLE);
//
//        // Swipe to refresh
//        swipeRefreshLayout.setVisibility(View.INVISIBLE);
//    }

    // clear shared pref for route
    public void clearRouteSharedPref() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


    // Reset textview and editTextView
    public void resetViews() {
        etRouteInfo.setText(R.string.stands_details);
        etSource.setText(R.string.source_id);
        etDestination.setText(R.string.destination_id);
        tvDistance.setText(R.string.distance);
        tvFare.setText(R.string.fare);
        fareAmount.setText("1");
    }


// adapter for bus type spinner
    public void busType(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bus_type_spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        busTypeSpinner.setAdapter(adapter);
    }

    // getter and setter for bus type spinner
    public String getBusTypeItem() {
        return busTypeItem;
    }
    public void setBusTypeItem(String busTypeItem) {
        this.busTypeItem = busTypeItem;
    }

    public Double getBASE_FARE() {
        return BASE_FARE;
    }
    public void setBASE_FARE(Double BASE_FARE) {
        this.BASE_FARE = BASE_FARE;
    }

    public String getFinalDistance() {
        return finalDistance;
    }

    public void setFinalDistance(String finalDistance) {
        this.finalDistance = finalDistance;
    }

    public String getFinalFare() {
        return finalFare;
    }

    public void setFinalFare(String finalFare) {
        this.finalFare = finalFare;
    }
}