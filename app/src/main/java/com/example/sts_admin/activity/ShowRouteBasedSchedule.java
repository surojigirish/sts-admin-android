package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    TextView route;
    List<ResultRouteSchedule> resultRouteScheduleList;
    RouteBasedScheduleAdapter.OnItemClickListener itemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route_based_schedule);


        String rid = String.valueOf(getRouteId());
        rvRouteScheduleList = findViewById(R.id.rv_route_schedule);
        route = findViewById(R.id.routeName);
        rvRouteScheduleList.setHasFixedSize(true);
        rvRouteScheduleList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        route.setText(getRouteInfo());

        getRouteScheduleList(rid);

        itemClickListener = new RouteBasedScheduleAdapter.OnItemClickListener() {
            @Override
            public void onScheduleItemClick(String id, String source, String destination, String arrivalTime, String departureTime,String duration) {

            }
        };



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
                        rvRouteScheduleList.setAdapter(new RouteBasedScheduleAdapter(resultRouteScheduleList, getApplicationContext(), new RouteBasedScheduleAdapter.OnItemClickListener() {
                            @Override
                            public void onScheduleItemClick(String id, String source, String destination, String arrivalTime, String departureTime,String duration) {

                                Intent i = new Intent(getApplicationContext(), UpdateScheduleData.class);
                                i.putExtra("sId", id);
                                i.putExtra("Ssource", source);
                                i.putExtra("Sdestination", destination);
                                i.putExtra("SarrivalAt", arrivalTime);
                                i.putExtra("SdepartureAt", departureTime);
                                i.putExtra("Sduration", duration);
                                startActivity(i);


                            }
                        }));

                    }

                }
            }

            @Override
            public void onFailure(Call<RouteScheduleResponse> call, Throwable t) {
                Log.i("TAG", "onResponse: error");
            }
        });

    }





    private String getRouteInfo() {
        Intent intent = getIntent();
        String routeName = intent.getStringExtra("routeSource") + " To "+intent.getStringExtra("routeDest");
        return routeName;
    }
    private int getRouteId(){
        Intent i = getIntent();
        return i.getIntExtra("routeId", -1);
    }
}