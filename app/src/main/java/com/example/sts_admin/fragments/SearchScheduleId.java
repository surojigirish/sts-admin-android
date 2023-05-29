package com.example.sts_admin.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.activity.AddBusSchedule;
import com.example.sts_admin.adapters.ScheduleAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.ScheduleResponse;
import com.example.sts_admin.model.Schedule;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchScheduleId extends Fragment {
    RecyclerView recycleViewScheduleItems;
    List<Schedule> scheduleList;
    ScheduleAdapter.OnScheduleItemClickListener onScheduleItemClickListener;


    SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_schedule_id, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycleViewScheduleItems = view.findViewById(R.id.recyclerView_schedule_details);

        recycleViewScheduleItems.setHasFixedSize(true);
        recycleViewScheduleItems.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedPrefManager = new SharedPrefManager(getContext());

        getAllSchedule();

        onScheduleItemClickListener = new ScheduleAdapter.OnScheduleItemClickListener() {
            @Override
            public void onClickListener(Integer scheduleId, String scheSource, String scheDestination) {

            }
        };

    }

    public void getAllSchedule(){
        Call<ScheduleResponse> scheduleResponseCall = Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().getAllSchedule();
        scheduleResponseCall.enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    scheduleList = response.body().getScheduleList();
                    recycleViewScheduleItems.setAdapter(new ScheduleAdapter(scheduleList, getContext(), new ScheduleAdapter.OnScheduleItemClickListener() {
                        @Override
                        public void onClickListener(Integer scheduleId, String scheSource, String scheDestination) {
                            Intent i = new Intent(getContext(), AddBusSchedule.class);
                            sharedPrefManager.saveSchedule(scheduleId, scheSource, scheDestination);
                            startActivity(i);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {

            }
        });
    }
}