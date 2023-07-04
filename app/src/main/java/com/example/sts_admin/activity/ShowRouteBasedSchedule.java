package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.RouteBasedScheduleAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.RouteScheduleResponse;
import com.example.sts_admin.model.results.ResultRouteSchedule;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowRouteBasedSchedule extends AppCompatActivity {


    RecyclerView rvRouteScheduleList;
    List<ResultRouteSchedule> resultRouteScheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route_based_schedule);
        String rid = String.valueOf(getRouteId());
        rvRouteScheduleList = findViewById(R.id.rv_route_schedule);
        rvRouteScheduleList.setHasFixedSize(true);
        rvRouteScheduleList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



        getRouteScheduleList(rid);


    }


    public void getRouteScheduleList(String rid){
        Integer routeId = Integer.valueOf(rid);
        Call<RouteScheduleResponse> responseCall = Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().showRouteSchedule(routeId);
        responseCall.enqueue(new Callback<RouteScheduleResponse>() {
            @Override
            public void onResponse(Call<RouteScheduleResponse> call, Response<RouteScheduleResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus() == 200 && response.body() != null){
                        resultRouteScheduleList =response.body().getResultRouteSchedule();
                        rvRouteScheduleList.setAdapter(new RouteBasedScheduleAdapter(resultRouteScheduleList, getApplicationContext()));

                    }

                }
            }

            @Override
            public void onFailure(Call<RouteScheduleResponse> call, Throwable t) {
                Log.i("TAG", "onResponse: error");
            }
        });

    }





    private int getRouteId() {
        Intent intent = getIntent();
        return intent.getIntExtra("routeId", -1);
    }
}