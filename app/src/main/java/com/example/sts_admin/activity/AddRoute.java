package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.RouteRequest;
import com.example.sts_admin.apiservice.response.RouteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRoute extends AppCompatActivity {

    EditText source,destination;
    AppCompatButton addNewRoute;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        source=findViewById(R.id.source);
        destination=findViewById(R.id.destination);
        addNewRoute=findViewById(R.id.add_route_btn);

        addNewRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                route(routeRequest());
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
                    if (response.body() != null && response.body().getStatus() == 200) {

                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

//                        Intent intent = new Intent(AddRoute.this, UpdateSchedule.class);
//                        startActivity(intent);
//                        finish();
                    } else {
                        Toast.makeText(AddRoute.this, "Failed To Add", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<RouteResponse> call, Throwable t) {

            }
        });
    }
}