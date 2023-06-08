package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.UpdateBusDetailsRequest;
import com.example.sts_admin.apiservice.response.UpdateBusDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusDetailsList extends AppCompatActivity {

    TextView busDetailsId, busDetailsCapacity, busDetailsStatus, busDetailsType;

    Button updateStatusType;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details_list);
//
        busDetailsId = findViewById(R.id.tv_bus_id);
        busDetailsCapacity= findViewById(R.id.tv_bus_capacity);
        busDetailsStatus = findViewById(R.id.tv_bus_status);
        busDetailsType= findViewById(R.id.tv_bus_details_type);
        updateStatusType=findViewById(R.id.updateStatusType);

        Intent i = getIntent();
        Integer busId = i.getIntExtra("busId",0);
        Integer busCapacity = i.getIntExtra("busCapacity",0);
        String busStatus = i.getStringExtra("busStatus");

        String busType = i.getStringExtra("busType");


        busDetailsCapacity.setText(busCapacity.toString());
        busDetailsId.setText(busId.toString());
        busDetailsStatus.setText(busStatus);
        busDetailsType.setText(busType);


        updateStatusType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBusDetails(busId, updateBusDetail());
            }
        });

    }



    public UpdateBusDetailsRequest updateBusDetail(){

        UpdateBusDetailsRequest updateBusDetailsRequest = new UpdateBusDetailsRequest();

        String updateStatus =  busDetailsStatus.getText().toString();
        String updateType = busDetailsType.getText().toString();

        updateBusDetailsRequest.setStatus(updateStatus);
        updateBusDetailsRequest.setType(updateType);


        return updateBusDetailsRequest;
    }


    //
    public void updateBusDetails(int busId, UpdateBusDetailsRequest updateBusDetailsRequest){

        Call<UpdateBusDetailsResponse> updateBusDetailsResponseCall = Client.getInstance(Consts.BASE_URL_BUS).getRoute().updateBusDetails(busId, updateBusDetailsRequest);

        updateBusDetailsResponseCall.enqueue(new Callback<UpdateBusDetailsResponse>() {
            @Override
            public void onResponse(Call<UpdateBusDetailsResponse> call, Response<UpdateBusDetailsResponse> response) {
                if (response.isSuccessful()){
                    Log.i("TAG", "onResponse: Bus Update Successfully");
                    Toast.makeText(BusDetailsList.this, "Successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateBusDetailsResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: unsuccessful");
            }
        });
    }
}