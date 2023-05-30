package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.BusScheduleListAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.BusScheduleDetailsResponse;
import com.example.sts_admin.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusScheduleList extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Result> busScheduleList;

    BusScheduleListAdapter.OnItemClickListener onItemClickListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedule_list);

        recyclerView=findViewById(R.id.bus_schedule_list_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



        getAllBusScheduleList();

//        onItemClickListener = new BusScheduleListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(String barcode) {
//
//            }
//        };

        onItemClickListener= new BusScheduleListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String driverBusId, String driverScheduleId) {
                // Handle the click event and initiate barcode scanning
                // You can start the ScannerActivity or any other barcode scanning logic here
            }
        };
    }


    public void getAllBusScheduleList(){
        Call<BusScheduleDetailsResponse> call = Client.getInstance(Consts.BASE_URL_BUS).getRoute().getAllBusScheduleList();
        call.enqueue(new Callback<BusScheduleDetailsResponse>() {
            @Override
            public void onResponse(Call<BusScheduleDetailsResponse> call, Response<BusScheduleDetailsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null &&  response.body().getStatus()==200){
                        busScheduleList= response.body().getResult();
                        recyclerView.setAdapter(new BusScheduleListAdapter(busScheduleList, getApplicationContext(), new BusScheduleListAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(String driverBusId,String driverScheduleId) {

                                Intent intent= new Intent(getApplicationContext(), BusScheduleInfoListScanner.class);
                                intent.putExtra("busId",driverBusId);
                                intent.putExtra("scheduleId",driverScheduleId);
                                startActivity(intent);
                                finish();
                            }
                        }));


                    }
                }
            }

            @Override
            public void onFailure(Call<BusScheduleDetailsResponse> call, Throwable t) {

            }
        });
    }
}