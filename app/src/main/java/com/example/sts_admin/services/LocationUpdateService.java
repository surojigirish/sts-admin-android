package com.example.sts_admin.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.LocationUpdate;
import com.example.sts_admin.model.BusSchedule;
import com.google.android.gms.location.LocationResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationUpdateService extends Service {

    private int busScheduleId;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocationUpdateService", "Service started");

        if (intent != null) {
            // Get putExtra from Intent
            busScheduleId = intent.getIntExtra("busScheduleId", -1);

            if (LocationResult.hasResult(intent)) {
                LocationResult locationResult = LocationResult.extractResult(intent);
                if (locationResult != null) {
                    Location location = locationResult.getLastLocation();
                    if (location != null) {
                        // Handle the received location update
                        handleLocationUpdate(location);
                    }
                }
            }
        }
        return START_NOT_STICKY;
    }

    private void handleLocationUpdate(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        // Log the location update
        Log.i("LocationUpdateService", "Received location update: lat = " + latitude + " long = " + longitude);

        // Create location update request object
        LocationUpdate request = createLocationUpdateRequest(latitude, longitude);

        // Make API call to update location
        updateLocation(request);
    }

    // API call in background service
    private void updateLocation(LocationUpdate request) {
        Call<Void> call = Client.getInstance(Consts.BASE_URL_LOCATION)
                .getRoute().updateLocation(busScheduleId, request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "onResponse: Location updated");
                    Toast.makeText(getApplicationContext(), "Location updated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("TAG", "onFailure: Error updating location", t);
                Toast.makeText(getApplicationContext(), "Error updating location", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Location request data for API call
    private LocationUpdate createLocationUpdateRequest(double latitude, double longitude) {
        LocationUpdate request = new LocationUpdate();

        request.setLat(latitude);
        request.setLng(longitude);
        return request;
    }

    // onDestroy
    @Override
    public void onDestroy() {
        super.onDestroy();

        stopForeground(true);
    }

    // onCreate
    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the notification channel for the foreground service
            NotificationChannel channel = new NotificationChannel(Consts.CHANNEL_ID, "Location Updates", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }



        // Create the notification to show the foreground service
        Notification notification = new NotificationCompat.Builder(this, Consts.CHANNEL_ID)
                .setContentTitle("Location Enabled")
                .setContentText("Current trip location running...")
                .setSmallIcon(R.drawable.bus_icon)
                .build();

        // Start the service as a foreground service
        startForeground(Consts.NOTIFICATION_ID, notification);
    }
}
