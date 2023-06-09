package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.GetPassengerDetailsAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.GetPassengerDetailResponse;
import com.example.sts_admin.model.Passengers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPassengerDetails extends AppCompatActivity {

    RecyclerView recyclerView;

    List<Passengers> passengerUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_passenger_details);

        recyclerView = findViewById(R.id.recyclerViewPassengerDetails);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        getAllPassengerDetails();

    }

    public void getAllPassengerDetails(){
        Call<GetPassengerDetailResponse> getPassengerDetailResponseCall = Client.getInstance(Consts.BASE_URL_USER).getRoute().getAllPassengerDetails();
        getPassengerDetailResponseCall.enqueue(new Callback<GetPassengerDetailResponse>() {
            @Override
            public void onResponse(Call<GetPassengerDetailResponse> call, Response<GetPassengerDetailResponse> response) {

                    if (response.body() !=null){
                        passengerUserList= response.body().getPassengers();
                        recyclerView.setAdapter(new GetPassengerDetailsAdapter(getApplicationContext(),passengerUserList));

                    }
                }

            @Override
            public void onFailure(Call<GetPassengerDetailResponse> call, Throwable t) {

            }
        });
    }
}