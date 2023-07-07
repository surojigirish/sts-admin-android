package com.example.sts_admin.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.activity.AddRoute;
import com.example.sts_admin.adapters.HaltAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.HaltResponse;
import com.example.sts_admin.model.Halts;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RouteSourceFragment extends Fragment {

    // RecyclerView
    RecyclerView sourceRecyclerView;
    List<Halts> availableHaltsList;
    // Click handler for sources
    HaltAdapter.OnHaltItemClickListener clickListener;

    // SharedPreferences to Handle route data
    SharedPreferences sf;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_route_source, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init views
        initializeViews(view);
        getSourceHalts();





    }

    private void initializeViews(View view) {
        sourceRecyclerView = view.findViewById(R.id.recyclerView_routeSource);
        sourceRecyclerView.setHasFixedSize(true);
        sourceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // SharedPreferences
        sf = requireActivity().getSharedPreferences("route-data", Context.MODE_PRIVATE);
    }

    private void getSourceHalts() {
        Call<HaltResponse> call = Client.getInstance(Consts.BASE_URL_BOOKING).getRoute().getAllHalts();
        call.enqueue(new Callback<HaltResponse>() {
            @Override
            public void onResponse(Call<HaltResponse> call, Response<HaltResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    availableHaltsList = response.body().getResult();

                    // setAdapter to RecyclerView
                    sourceRecyclerView.setAdapter(new HaltAdapter(getContext(), availableHaltsList, new HaltAdapter.OnHaltItemClickListener() {
                        @Override
                        public void onHaltItemClick(Integer id, String name, String latitude, String longitude) {
                            Log.i("TAG", "onHaltItemClick: id: " + id + " name: " +name);
                            storeSourceAndStartActivity(id, name);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<HaltResponse> call, Throwable t) {

            }
        });
    }

    private void storeSourceAndStartActivity(int id, String name) {
        editor = sf.edit();
        editor.putInt("sourceId", id);
        editor.putString("sourceName", name);
        editor.apply();

        // Go back to route activity with data
        Intent i = new Intent(getContext(), AddRoute.class);
        startActivity(i);
    }
}