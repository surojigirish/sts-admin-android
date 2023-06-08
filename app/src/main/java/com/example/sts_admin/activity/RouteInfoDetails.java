package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.GetRouteInfoDetailsAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.GetRouteInfoResponse;
import com.example.sts_admin.apiservice.response.RouteResponse;
import com.example.sts_admin.model.RouteInfoResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteInfoDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    List<RouteInfoResult> routeInfoResultList;

    GetRouteInfoDetailsAdapter.OnRouteInfoClickListener onRouteInfoClickListener;


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




                       // Setting Adapter
                       GetRouteInfoDetailsAdapter adapter = new GetRouteInfoDetailsAdapter( getApplicationContext(),routeInfoResultList, recyclerView);
                       // Setting on Swipe Listener
                       adapter.setOnDeleteClickListener(new GetRouteInfoDetailsAdapter.OnDeleteClickListener() {
                           @Override
                           public void onDeleteClick(int position) {
                               // Make Delete Api call and update the adapter
                               deleteRoute(position, adapter);
                           }
                       });

                       recyclerView.setAdapter(adapter);
                       adapter.enableSwipeToDelete();


                   }
               }
               recyclerView.setAdapter(new GetRouteInfoDetailsAdapter(getApplicationContext(), routeInfoResultList, new GetRouteInfoDetailsAdapter.OnRouteInfoClickListener() {
                   @Override
                   public void onRouteClick( String infoRouteDistance, String infoFare) {
                       Intent i = new Intent(getApplicationContext(),RouteInfoList.class);
//                               i.putExtra("infoRouteBusType",infoRouteBusType);
                       i.putExtra("infoRouteDistance",infoRouteDistance);
                       i.putExtra("infoRouteFare",infoFare);
                       startActivity(i);
                   }
               }));


           }

           @Override
           public void onFailure(Call<GetRouteInfoResponse> call, Throwable t) {

           }
       });
   }

    private void deleteRoute(int position, GetRouteInfoDetailsAdapter adapter) {
        RouteInfoResult routeInfo = routeInfoResultList.get(position);
        int routeId = routeInfo.getId();

        Call<RouteResponse> call = Client.getInstance(Consts.BASE_URL_SCHEDULE)
                .getRoute().deleteRoute(routeId);

        call.enqueue(new Callback<RouteResponse>() {
            @Override
            public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    if (response.body() != null && response.body().getStatus() == 200) {
                        Toast.makeText(RouteInfoDetails.this, "Route deleted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RouteInfoDetails.this, "Failed to delete route", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle error response
                    Toast.makeText(RouteInfoDetails.this, "Could not make it to the server, sorry", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RouteResponse> call, Throwable t) {
                // Handle failure
            }
        });

        // Notify the adapter of the delete action
        routeInfoResultList.remove(position);
        adapter.notifyItemRemoved(position);
    }
}