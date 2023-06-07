package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.GetRouteDetailsAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.GetRouteResponse;
import com.example.sts_admin.model.AddRouteDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteDetails extends AppCompatActivity {

    RecyclerView recyclerView;

    List<AddRouteDetails> addRouteDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);

        recyclerView = findViewById(R.id.recyclerViewRouteDetails);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getAllRouteList();
    }


    private void getAllRouteList(){
        Call<GetRouteResponse> getRouteResponseCall = Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().getAllRouteList();

        getRouteResponseCall.enqueue(new Callback<GetRouteResponse>() {
            @Override
            public void onResponse(Call<GetRouteResponse> call, Response<GetRouteResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        addRouteDetailsList = response.body().getAddRouteDetailsList();
                        recyclerView.setAdapter(new GetRouteDetailsAdapter(addRouteDetailsList,getApplicationContext()));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetRouteResponse> call, Throwable t) {

            }
        });
    }
}