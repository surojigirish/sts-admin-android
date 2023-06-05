package com.example.sts_admin.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.activity.AddBusSchedule;
import com.example.sts_admin.adapters.DriverAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.EmployeeDriverResponse;
import com.example.sts_admin.model.Driver;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchDriver extends Fragment {

    RecyclerView recyclerView;
    List<Driver> driverList;
    SharedPrefManager sharedPrefManager;
    DriverAdapter.OnItemClickListener onItemClickListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_driver, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView_driver);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getAllDrivers();

  onItemClickListener = new DriverAdapter.OnItemClickListener() {
      @Override
      public void onClickItem(Integer driverId, String driverFirstName, String driverLastName,String driverLicenseNo,String driverContact,String driverGender, String driverEmployeeNo) {

      }
  };
    }


    private void getAllDrivers() {

        Call<EmployeeDriverResponse> call = Client.getInstance(Consts.BASE_URL_ADMIN).getRoute().getDrivers(getUserSession());
        call.enqueue(new Callback<EmployeeDriverResponse>() {
            @Override
            public void onResponse(Call<EmployeeDriverResponse> call, Response<EmployeeDriverResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        driverList = response.body().getEmployee();
                        recyclerView.setAdapter(new DriverAdapter(driverList, getContext(), new DriverAdapter.OnItemClickListener(){
                            @Override
                            public void onClickItem(Integer driverId, String driverFirstName, String driverLastName,String driverLicenseNo,String driverContact,String driverGender, String driverEmployeeNo) {
                                Intent i = new Intent(getContext(), AddBusSchedule.class);
                                i.putExtra("driverId", driverId);
                                i.putExtra("driverFirstName", driverFirstName);
                                i.putExtra("driverLastName", driverLastName);
                                startActivity(i);
                            }
                        }));

                    }
                } else {
                    if (response.body() != null) {
                        Toast.makeText(getContext(), response.body().getStatus(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<EmployeeDriverResponse> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure: " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getUserSession() {
        sharedPrefManager = new SharedPrefManager(getContext());

        return sharedPrefManager.getUser().getToken();
    }
}