package com.example.sts_admin.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.sts_admin.activity.RouteInfoActivity;
import com.example.sts_admin.adapters.HaltAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.HaltResponse;
import com.example.sts_admin.model.Halts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationSearchFragment extends Fragment {
    RecyclerView recyclerViewDestinations;
    private List<Halts> halts;

    // onClick listener
    HaltAdapter.OnHaltItemClickListener onDestinationItemClick;

    // save data to shared pref
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_destination_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        // init shared pref
        sharedPreferences = requireActivity().getSharedPreferences("route", Context.MODE_PRIVATE);

        getDestination();

        // onDestination click
        onDestinationItemClick = new HaltAdapter.OnHaltItemClickListener() {
            @Override
            public void onHaltItemClick(Integer id, String name, String latitude, String longitude) {
                Log.i("TAG", "onViewCreated->onHaltItemClick: " +name);
                setDestinationInfo(id, name, latitude, longitude);
            }
        };
    }

    public void initViews(View view) {
        recyclerViewDestinations = view.findViewById(R.id.recyclerView_destination);
        recyclerViewDestinations.setHasFixedSize(true);
        recyclerViewDestinations.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void setDestinationInfo(Integer destinationId, String name, String latitude, String longitude) {
        // store data in shared pref
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("destinationId", destinationId);
        editor.putString("destinationName", name);
        editor.putString("destinationLat", latitude);
        editor.putString("destinationLong", longitude);
        editor.apply();

        // go back to route detail activity
        Intent intent = new Intent(getContext(), RouteInfoActivity.class);
        startActivity(intent);
    }

    public void getDestination() {
        Call<HaltResponse> haltResponseCall = Client.getInstance(Consts.BASE_URL_BOOKING).getRoute().getAllHalts();
        haltResponseCall.enqueue(new Callback<HaltResponse>() {
            @Override
            public void onResponse(Call<HaltResponse> call, Response<HaltResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    halts = response.body().getResult();
                    recyclerViewDestinations.setAdapter(new HaltAdapter(getContext(), halts, new HaltAdapter.OnHaltItemClickListener() {
                        @Override
                        public void onHaltItemClick(Integer id, String name, String latitude, String longitude) {
                            setDestinationInfo(id, name, latitude, longitude);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<HaltResponse> call, Throwable t) {
            }
        });
    }
}