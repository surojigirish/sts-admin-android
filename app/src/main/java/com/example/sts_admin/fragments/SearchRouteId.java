package com.example.sts_admin.fragments;

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
import com.example.sts_admin.adapters.RouteAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.RouteResponse;
import com.example.sts_admin.model.RouteInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRouteId extends Fragment {

    RecyclerView recyclerViewRouteId;
    List<RouteInfo> routeInfoList;     // Bug arise needs fix, requires a route-info model
    // placed RouteModel class for time being, need to changes to better alternative

    RouteAdapter.OnItemClickListener onRouteItemClickListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_route_id, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerViewRouteId=view.findViewById(R.id.recyclerViewRouteId);
        recyclerViewRouteId.setHasFixedSize(true);
        recyclerViewRouteId.setLayoutManager(new LinearLayoutManager(getContext()));

        getRoutesInfo();

        onRouteItemClickListener = new RouteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Integer routeId, String routeDestination, String routeSource) {

            }
        };
    }

    public void getRoutesInfo(){
        Call<RouteResponse> routeResponseCall= Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().getRoutesInfo();
        routeResponseCall.enqueue(new Callback<RouteResponse>() {
            @Override
            public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    routeInfoList = response.body().getResult();
                    /*recyclerViewRouteId.setAdapter(new RouteAdapter(getContext(), routeInfoList, new RouteAdapter.OnItemClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onItemClick(Integer routeId, String routeDestination, String routeSource) {
                            Intent i= new Intent(getContext(), AddSchedule.class);
                            i.putExtra("routeId",routeId);
                            i.putExtra("source", routeSource);
                            i.putExtra("destination", routeDestination);
                            startActivity(i);
                        }}));*/
                }
            }

            @Override
            public void onFailure(Call<RouteResponse> call, Throwable t) {

            }
        });
    }
}