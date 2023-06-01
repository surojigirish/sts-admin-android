package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.BusScheduleListAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.BusScheduleDetailsResponse;
import com.example.sts_admin.apiservice.response.MainResponse;
import com.example.sts_admin.model.results.ListOfBusSchedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusScheduleList extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ListOfBusSchedule> driverBusScheduleList;

    BusScheduleListAdapter.OnDriverBusScheduleClick clickListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedule_list);

        recyclerView=findViewById(R.id.bus_schedule_list_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



        allDriverSchedules();

        clickListener = new BusScheduleListAdapter.OnDriverBusScheduleClick() {
            @Override
            public void onItemClick(Integer busScheduleId) {

            }
        };
    }

    // Driver schedules API call
    private void allDriverSchedules() {
        Call<MainResponse> call = Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().driverBusSchedules(4);
        call.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getStatusCode() == 200) {
                        driverBusScheduleList = response.body().getListOfBusSchedule();
                        recyclerView.setAdapter(new BusScheduleListAdapter(driverBusScheduleList, new BusScheduleListAdapter.OnDriverBusScheduleClick() {
                            @Override
                            public void onItemClick(Integer busScheduleId) {
                                Log.i("DRIVER SCHEDULE", "onItemClick: bus-schedule " + busScheduleId);
                                Intent intent= new Intent(getApplicationContext(), BusScheduleInfoListScanner.class);
                                intent.putExtra("busScheduleId", busScheduleId);
                                startActivity(intent);
                                finish();
                            }
                        }));
                    }
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {

            }
        });
    }
}