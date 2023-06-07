package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.GetRouteInfoDetailsAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.GetRouteInfoResponse;
import com.example.sts_admin.apiservice.response.RouteInfoResponse;
import com.example.sts_admin.model.RouteInfoResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteInfoDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    List<RouteInfoResult> routeInfoResultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_info_details);

        recyclerView = findViewById(R.id.recyclerViewRouteInfoDetails);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getAllRouteInfoDetails();

    }

   public void getAllRouteInfoDetails(){
       Call<GetRouteInfoResponse> getRouteInfoResponseCall = Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().getAllRouteInfoDetails();

       getRouteInfoResponseCall.enqueue(new Callback<GetRouteInfoResponse>() {
           @Override
           public void onResponse(Call<GetRouteInfoResponse> call, Response<GetRouteInfoResponse> response) {
               if (response.isSuccessful()){
                   if (response.body() != null){
                       routeInfoResultList = response.body().getRouteInfoResultList();
                       recyclerView.setAdapter(new GetRouteInfoDetailsAdapter(getApplicationContext(),routeInfoResultList));
                   }
               }
           }

           @Override
           public void onFailure(Call<GetRouteInfoResponse> call, Throwable t) {

           }
       });
   }
}