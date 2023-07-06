package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.GetPassengerListAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.GetPassengerDetailResponse;
import com.example.sts_admin.model.Passengers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPassengerList extends AppCompatActivity {

    RecyclerView recyclerView;

    List<Passengers> passengerUserList;

    GetPassengerListAdapter.OnPassengerItemClickListener onPassengerItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_passenger_details);

        recyclerView = findViewById(R.id.recyclerViewPassengerDetails);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));




    }

    @Override
    protected void onStart() {
        super.onStart();
        getAllPassengerDetails();

        onPassengerItemClickListener = new GetPassengerListAdapter.OnPassengerItemClickListener() {
            @Override
            public void onClickListener(Integer passengerId, String passengerName, String passengerAddress, String passengerGender, String passengerCategory, String passengerContact, String passengerEmailId, String passengerPhoto) {

            }
        };
    }

    public void getAllPassengerDetails(){
        Call<GetPassengerDetailResponse> getPassengerDetailResponseCall = Client.getInstance(Consts.BASE_URL_USER).getRoute().getAllPassengerDetails();
        getPassengerDetailResponseCall.enqueue(new Callback<GetPassengerDetailResponse>() {
            @Override
            public void onResponse(Call<GetPassengerDetailResponse> call, Response<GetPassengerDetailResponse> response) {

                    if (response.body() !=null){
                        passengerUserList= response.body().getPassengers();
                        recyclerView.setAdapter(new GetPassengerListAdapter(getApplicationContext(), passengerUserList, new GetPassengerListAdapter.OnPassengerItemClickListener() {
                            @Override
                            public void onClickListener(Integer passengerId, String passengerName, String passengerAddress, String passengerGender, String passengerCategory, String passengerContact, String passengerEmailId,String passengerPhoto) {
                                Intent i = new Intent(getApplicationContext(), ViewPassengerDetails.class);
                                i.putExtra("pName", passengerName);
                                i.putExtra("pAddress", passengerAddress);
                                i.putExtra("pGender", passengerGender);
                                i.putExtra("pCategory", passengerCategory);
                                i.putExtra("pContact", passengerContact);
                                i.putExtra("pEmail", passengerEmailId);
                                i.putExtra("pPhoto", passengerPhoto);
                                startActivity(i);
                            }
                        }));

                    }
                }

            @Override
            public void onFailure(Call<GetPassengerDetailResponse> call, Throwable t) {

            }
        });
    }
}