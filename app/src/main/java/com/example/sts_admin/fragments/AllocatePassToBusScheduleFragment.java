package com.example.sts_admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sts_admin.R;
import com.example.sts_admin.adapters.ShuttleBusScheduleAdapter;
import com.example.sts_admin.model.results.ListOfBusSchedule;

import java.util.List;

public class AllocatePassToBusScheduleFragment extends Fragment {

    // Bus schedule list variables
    private RecyclerView busScheduleRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ListOfBusSchedule> vBusScheduleList;

    // On Bus schedule item click handler
    ShuttleBusScheduleAdapter.OnBusScheduleClickListener onBusScheduleItemClick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_allocate_pass_to_bus_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}