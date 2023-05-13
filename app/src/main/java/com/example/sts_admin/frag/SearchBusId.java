package com.example.sts_admin.frag;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.activity.AddBusSchedule;
import com.example.sts_admin.adapters.BusAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.BusScheduleResponse;
import com.example.sts_admin.model.Bus;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBusId extends Fragment {
    RecyclerView recyclerViewBusItems;
    List<Bus> busList;

    BusAdapter.OnBusItemClickListener onBusItemClickListener;

    SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_bus_id, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewBusItems = view.findViewById(R.id.recyclerView_bus_details);

        recyclerViewBusItems.setHasFixedSize(true);
        recyclerViewBusItems.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedPrefManager = new SharedPrefManager(getContext());
        getAllBuses();

        onBusItemClickListener = new BusAdapter.OnBusItemClickListener() {
            @Override
            public void onClickListener(Integer busId, String busRegNo) {

            }
        };
    }
    public void getAllBuses(){
        Call<BusScheduleResponse> busScheduleResponseCall = Client.getInstance(Consts.BASE_URL_BUS).getRoute().getAllBus();
        busScheduleResponseCall.enqueue(new Callback<BusScheduleResponse>() {
            @Override
            public void onResponse(Call<BusScheduleResponse> call, Response<BusScheduleResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    busList = response.body().getBusses();
                    recyclerViewBusItems.setAdapter(new BusAdapter(busList, getContext(), new BusAdapter.OnBusItemClickListener() {
                        @Override
                        public void onClickListener(Integer busId, String busRegNo) {
                            Intent i = new Intent(getContext(), AddBusSchedule.class);
                            Log.i("TAG", "onClickListener: " +busId);
                            sharedPrefManager.saveBus(busId, busRegNo);
                            /*i.putExtra("busId",busId);
                            i.putExtra("busRegNo",busRegNo);*/

                            startActivity(i);



                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<BusScheduleResponse> call, Throwable t) {

            }
        });
    }
}