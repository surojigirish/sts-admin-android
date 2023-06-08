package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.GetBusDetailsAdapter;
import com.example.sts_admin.adapters.GetRouteDetailsAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.GetBusResponse;
import com.example.sts_admin.apiservice.response.RouteResponse;
import com.example.sts_admin.model.AddRouteDetails;
import com.example.sts_admin.model.BusResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusDetails extends AppCompatActivity {

    RecyclerView recyclerView;

    List<BusResult> busResultList;

    GetBusDetailsAdapter.OnBusDetailsClickListener onBusDetailsClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);

        recyclerView = findViewById(R.id.recyclerViewBusList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getAllBusList();

        onBusDetailsClickListener = new GetBusDetailsAdapter.OnBusDetailsClickListener() {
            @Override
            public void onBusDetailsClick(Integer busCapacity, Integer busId, String busStatus, String busType) {

            }
        };
    }



    public void getAllBusList(){
        Call<GetBusResponse> getBusResponseCall = Client.getInstance(Consts.BASE_URL_BUS).getRoute().getAllBusList();

        getBusResponseCall.enqueue(new Callback<GetBusResponse>() {
            @Override
            public void onResponse(Call<GetBusResponse> call, Response<GetBusResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        busResultList = response.body().getBusResultList();
//                        recyclerView.setAdapter(new GetBusDetailsAdapter(getApplicationContext(), busResultList, new GetBusDetailsAdapter.OnBusDetailsClickListener() {
//                            @Override
//                            public void onBusDetailsClick(Integer busCapacity, Integer busId, String busStatus, String busType) {
//                                Intent i = new Intent(getApplicationContext(),BusDetailsList.class);
//
//                                i.putExtra("busCapacity",busCapacity);
//                                i.putExtra("busId",busId);
//                                i.putExtra("busStatus",busStatus);
//                                i.putExtra("busType",busType);
//                                startActivity(i);
//                            }
//                        }));


                        // Setting Adapter
                        GetBusDetailsAdapter adapter = new GetBusDetailsAdapter(getApplicationContext(), busResultList, recyclerView);
                        // Setting on Swipe Listener
                        adapter.setOnDeleteClickListener(new GetBusDetailsAdapter.OnDeleteClickListener() {
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
            }

            @Override
            public void onFailure(Call<GetBusResponse> call, Throwable t) {

            }
        });
    }

    private void deleteRoute(int position, GetBusDetailsAdapter adapter) {
        BusResult busResult = busResultList.get(position);
        int busId = busResult.getId();

        Call<RouteResponse> call = Client.getInstance(Consts.BASE_URL_SCHEDULE)
                .getRoute().deleteRoute(busId);

        call.enqueue(new Callback<RouteResponse>() {
            @Override
            public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    if (response.body() != null && response.body().getStatus() == 200) {
                        Toast.makeText(BusDetails.this, "Route deleted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BusDetails.this, "Failed to delete route", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle error response
                    Toast.makeText(BusDetails.this, "Could not make it to the server, sorry", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RouteResponse> call, Throwable t) {
                // Handle failure
            }
        });

        // Notify the adapter of the delete action
        busResultList.remove(position);
        adapter.notifyItemRemoved(position);
    }
}