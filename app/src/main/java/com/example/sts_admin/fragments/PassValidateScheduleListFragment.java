package com.example.sts_admin.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sts_admin.Assets;
import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.ShuttleBusScheduleAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.MainResponse;
import com.example.sts_admin.model.BusSchedule;
import com.example.sts_admin.model.results.ListOfBusSchedule;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassValidateScheduleListFragment extends Fragment {

    // Bus schedule list variables
    private RecyclerView busScheduleRecyclerView;
    private List<ListOfBusSchedule> vBusScheduleList;
    // On Bus schedule item click handler
    ShuttleBusScheduleAdapter.OnBusScheduleClickListener onBusScheduleItemClick;
    // Bus Schedule instance variables to handle date and bus-schedule id
    private BusSchedule onBusScheduleClickedData;


    // Scanner variables
    private SurfaceView scannerCameraPreview;
    private CameraSource cameraSource;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pass_validate_schedule_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        getShuttleBusScheduleListData();

        // Bus schedule list item click listener
        onBusScheduleItemClick = new ShuttleBusScheduleAdapter.OnBusScheduleClickListener() {
            @Override
            public void onItemClick(Integer busId, String scheduleDate) {
                Log.i("TAG", "onViewCreated->onBusScheduleItemClick: " + busId);
            }
        };

        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(requireContext())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(requireContext(), barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

        // Scanner preview to scan barcode
        scannerCameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(requireActivity(),
                            new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    startCamera();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        // Barcode Detector setup function
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(@NonNull Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() > 0) {
                    // Add Handler to handle the Toast on main thread looper
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            String barcodeValue = barcodes.valueAt(0).displayValue;

                            // print the message
                            Toast.makeText(requireContext(), "value " + barcodeValue, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    // Initialize the views
    private void initViews(View v) {
        // Scanner view init
        scannerCameraPreview = v.findViewById(R.id.scanner_camera_preview);

        // List of bus schedule recycler init
        busScheduleRecyclerView = v.findViewById(R.id.bus_schedule_recycler);
        busScheduleRecyclerView.setHasFixedSize(true);
        busScheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // instance of Bus Schedule model class
        onBusScheduleClickedData = new BusSchedule();
    }

    // Start camera function
    private void startCamera() {
        try {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            cameraSource.start(scannerCameraPreview.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // API call to get all bus schedule list and filter to show only shuttle buses
    private void getShuttleBusScheduleListData() {
        // Current date
        LocalDate date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = Assets.getCurrentDate();
        }
        Log.i("TAG", "getShuttleBusScheduleListData: date " + date);

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

                                Log.i("TAG", "onItemClick: bus-id " + onBusScheduleClickedData.getId());
                                Log.i("TAG", "onItemClick: schedule date " + onBusScheduleClickedData.getDate());
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
}