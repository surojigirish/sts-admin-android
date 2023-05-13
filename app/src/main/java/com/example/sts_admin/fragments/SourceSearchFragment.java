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


public class SourceSearchFragment extends Fragment {

    RecyclerView recyclerViewSources;
    List<Halts> halts;

    HaltAdapter.OnHaltItemClickListener onSourceItemClickListener;

    // save data to shared pref manager
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_halt_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        // sharedpref
        sharedPreferences = requireActivity().getSharedPreferences("route", Context.MODE_PRIVATE);
        getSource();

        // source click listener
        onSourceItemClickListener = new HaltAdapter.OnHaltItemClickListener() {
            @Override
            public void onHaltItemClick(Integer id, String name, String latitude, String longitude) {
                Log.i("TAG", "onSourceClick: " + name);
                setSourceInfo(id, name, latitude, longitude);
            }
        };
    }

    public void initViews(View view) {
        // RecyclerView init
        recyclerViewSources = view.findViewById(R.id.recyclerView_sources);
        recyclerViewSources.setHasFixedSize(true);
        recyclerViewSources.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void getSource() {
        Call<HaltResponse> haltResponseCall = Client.getInstance(Consts.BASE_URL_BOOKING).getRoute().getAllHalts();
        haltResponseCall.enqueue(new Callback<HaltResponse>() {
            @Override
            public void onResponse(Call<HaltResponse> call, Response<HaltResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    halts = response.body().getResult();
                    recyclerViewSources.setAdapter(new HaltAdapter(getContext(), halts, new HaltAdapter.OnHaltItemClickListener() {
                        @Override
                        public void onHaltItemClick(Integer id, String name, String latitude, String longitude) {
                            setSourceInfo(id, name, latitude, longitude);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<HaltResponse> call, Throwable t) {

            }
        });
    }

    // set source info
    public void setSourceInfo(Integer sourceId, String name, String latitude, String longitude) {
        // store data in shared pref
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("sourceId", sourceId);
        editor.putString("sourceName", name);
        editor.putString("sourceLat", latitude);
        editor.putString("sourceLong", longitude);
        editor.apply();

        // go back to route detail activity
        Intent intent = new Intent(getContext(), RouteInfoActivity.class);
        startActivity(intent);
    }
}