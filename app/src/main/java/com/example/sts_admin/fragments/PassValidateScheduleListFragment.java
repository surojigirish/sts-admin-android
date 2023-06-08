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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import com.example.sts_admin.apiservice.request.ValidationRequest;
import com.example.sts_admin.apiservice.response.MainResponse;
import com.example.sts_admin.model.BusSchedule;
import com.example.sts_admin.model.PassValidation;
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

    // QR data store
    PassValidation passValidation;


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
            private boolean isToastDisplayed = false; // Flag to track if a toast is already displayed

            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(@NonNull Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() > 0 && !isToastDisplayed) {
                    // Set the flag to true to prevent multiple toasts
                    isToastDisplayed = true;

                    // Add Handler to handle the Toast on main thread looper
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            // Extract the barcode value
                            String barcodeValue = barcodes.valueAt(0).displayValue;

                            // Decode the QR data
                            String[] qrValues = barcodeValue.split("\\|");
                            if (qrValues.length >= 2) {
                                String stringPassId = qrValues[0].trim();
                                String stringPassengerId = qrValues[1].trim();

                                // Convert data from String to Integer
                                Integer passId = Integer.valueOf(stringPassId);
                                Integer passengerId = Integer.valueOf(stringPassengerId);

                                // Pass the qrValues data to next fragment to allocate a bus-schedule to pass
                                AllocatePassToBusScheduleFragment allocateFragment = new AllocatePassToBusScheduleFragment();
                                Bundle args = new Bundle();
                                args.putStringArray("qrValues", qrValues);
                                allocateFragment.setArguments(args);

                                // Start the next fragment
                                getParentFragmentManager().beginTransaction()
                                                .replace(R.id.frameLayout_validate_pass, allocateFragment)
                                                        .commit();

                                // Toast the message
                                Toast.makeText(requireContext(), "Pass ID: " + stringPassId + " PassengerId: " + stringPassengerId, Toast.LENGTH_SHORT).show();
                            }

                            // Use a Handler to reset the flag after a delay (1 second)
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Reset the flag to allow further toasts
                                    isToastDisplayed = false;
                                }
                            }, 1000); // Adjust the delay as needed
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

        // instance of PassValidation model class
        passValidation = new PassValidation();
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

}