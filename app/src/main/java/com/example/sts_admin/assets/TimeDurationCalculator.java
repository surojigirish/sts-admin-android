package com.example.sts_admin.assets;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class TimeDurationCalculator {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String calculateDuration(String departureTime, String arrivalTime) {
        if (departureTime == null || arrivalTime == null) {
            return ""; // Return an empty string if either value is null
        }

        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());

        try {
            // Parse the departure and arrival times as Date objects
            Date departure = format.parse(departureTime);
            Date arrival = format.parse(arrivalTime);

            // Calculate the duration in milliseconds
            long durationInMillis = arrival.getTime() - departure.getTime();

            // Calculate the duration in minutes
            long durationInMinutes = durationInMillis / (60 * 1000);

            // Return the duration in minutes
            return String.valueOf(durationInMinutes);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ""; // Return an empty string if there was an error
    }

}
