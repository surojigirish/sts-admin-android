package com.example.sts_admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sts_admin.Assets;
import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.ShuttleBusScheduleAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.ValidationRequest;
import com.example.sts_admin.apiservice.response.MainResponse;
import com.example.sts_admin.model.BusSchedule;
import com.example.sts_admin.model.PassValidation;
import com.example.sts_admin.model.results.ListOfBusSchedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllocatePassToBusScheduleFragment extends Fragment {

    // Bus schedule list variables
    private RecyclerView busScheduleRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ListOfBusSchedule> vBusScheduleList;

    // On Bus schedule item click handler
    ShuttleBusScheduleAdapter.OnBusScheduleClickListener onBusScheduleItemClick;


    // Bus Schedule instance variables to handle date and bus-schedule id
    private BusSchedule onBusScheduleClickedData;

    // Pass
    private PassValidation passData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_allocate_pass_to_bus_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        initializeViews(view);

        // get list of shuttle buses
        getShuttleBusScheduleListData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // refresh list of shuttle buses
                getShuttleBusScheduleListData();
            }
        });



    }

    // Initialize views
    private void initializeViews(View v) {
        // Swipe to refresh
        swipeRefreshLayout = v.findViewById(R.id.swipeToRefreshBusSchedule);

        // instance of Bus Schedule model class
        onBusScheduleClickedData = new BusSchedule();
        passData = new PassValidation();

        // get data from frag
        getBundleData();

        // List of bus schedule recycler init
        busScheduleRecyclerView = v.findViewById(R.id.bus_schedule_recycler);
        busScheduleRecyclerView.setHasFixedSize(true);
        busScheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    // API call to get all bus schedule list and filter to show only shuttle buses
    private void getShuttleBusScheduleListData() {
        // Current date
        LocalDate date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = Assets.getCurrentDate();
        }
        Log.i("TAG", "getShuttleBusScheduleListData: date " + date);

        // Api Call to get bus schedule list
        Call<MainResponse> call = Client.getInstance(Consts.BASE_URL_SCHEDULE)
                .getRoute().getBusScheduleOnDate(String.valueOf(date));

        call.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getStatusCode() == 200) {
                        // add response to vBusScheduleList
                        vBusScheduleList = response.body().getListOfBusSchedule();
                        // Filter shuttle buses from the vBusScheduleList
                        List<ListOfBusSchedule> shuttleBusList = filterShuttleBuses(vBusScheduleList);
                        // add the list to recyclerView instance of bus list
                        busScheduleRecyclerView.setAdapter(new ShuttleBusScheduleAdapter(shuttleBusList, new ShuttleBusScheduleAdapter.OnBusScheduleClickListener() {
                            @Override
                            public void onItemClick(Integer busId, String scheduleDate) {
                                onBusScheduleClickedData.setId(busId);
                                onBusScheduleClickedData.setDate(scheduleDate);

                                int passId = passData.getPassId();
                                int passengerId = passData.getPassengerId();
                                validatePass(passengerId, passId, passValidationRequest());
                            }
                        }));
                    }
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: t " + t);
            }
        });

        // Notify the SwipeRefreshLayout that the refresh action has finished
        swipeRefreshLayout.setRefreshing(false);
    }


    // Function to filter list of shuttle buses schedule
    private List<ListOfBusSchedule> filterShuttleBuses(List<ListOfBusSchedule> busScheduleList) {
        // Create a new List to Store Shuttle Buses schedule
        List<ListOfBusSchedule> shuttleBusList = new ArrayList<>();
        int filtered = 0, loop = 0;

        // Iterate through Bus Schedules
        for (ListOfBusSchedule busSchedule : busScheduleList) {
            loop++;
            Log.i("TAG", "filterShuttleBuses: loop " + loop);
            // Filter bus-type SHUTTLE and seats-available greater than 0
            if (busSchedule.getBus().getType().equals("SHUTTLE") && busSchedule.getBusSchedule().getSeatsAvailable() > 0) {
                filtered++;
                Log.i("TAG", "filterShuttleBuses: filtered " + filtered);
                shuttleBusList.add(busSchedule);
            }
        }
        // Return shuttleBusList
        return shuttleBusList;
    }

    // Pass validation request function
    private ValidationRequest passValidationRequest() {
        ValidationRequest request = new ValidationRequest();
        // get request data from Bus Schedule instance
        Integer busScheduleId = onBusScheduleClickedData.getId();
        Log.i("TAG", "passValidationRequest: bus-schedule-id " +busScheduleId);
        String date = onBusScheduleClickedData.getDate();
        // get current time stamp
        String timeStamp = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            timeStamp = Assets.getFormattedDate();
            Log.i("TAG", "passValidationRequest: timeStamp " +timeStamp);
        }
        // set request data
        request.setBusScheduleId(busScheduleId);
        request.setTravelDate(date);
        request.setBookingTimeStamp(timeStamp);

        return request;
    }

    // API call to validate pass and passenger
    private void validatePass(Integer passengerId, Integer passId, ValidationRequest request) {

        Call<MainResponse> call = Client.getInstance(Consts.BASE_URL_BOOKING).getRoute().validatePass(passengerId, passId, request);
        call.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getStatusCode() == 200) {
                        Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("TAG", "onResponse 200: Pass and Passenger allocated a bus " + response.body().getMessage());
                        PassValidateScheduleListFragment backToPassScanner = new PassValidateScheduleListFragment();
                        // Start the next fragment
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout_validate_pass, backToPassScanner)
                                .commit();
                    } else if (response.body() != null && response.body().getStatusCode() == 400) {
                        Log.i("TAG", "onResponse 400: Pass and Passenger not allocated a bus " + response.body().getMessage());

                    }
                } else {
                    Toast.makeText(requireContext(), "Pass is already used twice today", Toast.LENGTH_SHORT).show();
                    PassValidateScheduleListFragment backToPassScanner = new PassValidateScheduleListFragment();
                    // Start the next fragment
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout_validate_pass, backToPassScanner)
                            .commit();
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: pass validation " + t);
            }
        });
    }

    private void getBundleData() {
        // Retrieve the qrValues data from the arguments
        String[] qrValues = getArguments().getStringArray("qrValues");
        if (qrValues != null && qrValues.length >= 2) {
            String stringPassId = qrValues[0].trim();
            String stringPassengerId = qrValues[1].trim();

            // Convert data from String to Integer
            Integer passId = Integer.valueOf(stringPassId);
            Integer passengerId = Integer.valueOf(stringPassengerId);

            passData.setPassId(passId);
            passData.setPassengerId(passengerId);
        }
    }


}