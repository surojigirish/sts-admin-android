package com.example.sts_admin.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
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
import com.example.sts_admin.adapters.RouteAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.RouteResponse;
import com.example.sts_admin.model.Route;
import com.example.sts_admin.model.RouteInfo;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteSearchFragment extends Fragment {

    RecyclerView recyclerViewRoute;
    SearchView searchViewRoute;
    List<RouteInfo> routes;

    RouteAdapter.OnItemClickListener routeItemClickListener;

    // save data to shared pref manager
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_route_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        // sharedpref
        sharedPreferences = requireActivity().getSharedPreferences("route", Context.MODE_PRIVATE);

        getRoutes();

        // route item clicked
        routeItemClickListener = new RouteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Integer routeId, String routeDestination, String routeSource) {

            }
        };
    }

    public void initViews(View view) {
        // RecyclerView init
        recyclerViewRoute = view.findViewById(R.id.recyclerView_routes);
        recyclerViewRoute.setHasFixedSize(true);
        recyclerViewRoute.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    public void getRoutes() {
        Call<RouteResponse> routeResponseCall = Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().getRoutes();
        routeResponseCall.enqueue(new Callback<RouteResponse>() {
            @Override
            public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        routes = response.body().getResult();
                        recyclerViewRoute.setAdapter(new RouteAdapter(getContext(), routes, new RouteAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Integer routeId, String routeDestination, String routeSource) {
                                Log.i("TAG", "onItemClick: " +routeId + " " + routeDestination);
                                setRouteInfo(routeId, routeDestination, routeSource);
                            }
                        }));
                    }
                } else {
                    if (response.body() != null) {
                        Log.i("TAG", "onResponse: " + response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<RouteResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public void setRouteInfo(int routeId, String destination, String source) {
        // store data in shared pref
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("routeId", routeId);
        editor.putString("routeDestination", destination);
        editor.putString("routeSource", source);
        editor.apply();

        // go back to route detail activity
        Intent intent = new Intent(getContext(), RouteInfoActivity.class);
        startActivity(intent);
    }
}