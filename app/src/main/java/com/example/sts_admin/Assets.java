package com.example.sts_admin;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Assets {

    private static final LocalDate currentDate = LocalDate.now();

    /*// Can use directly without create an instance of Assets
    static {
        currentDate = LocalDate.now();
    }*/

    public static LocalDate getCurrentDate() {
        return currentDate;
    }


    /*
    * To get current time stamp in human readable format
    * */
    static long currentTimeMillis = System.currentTimeMillis();
    private static final Date timeStamp = new Date(currentTimeMillis);

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static final String formattedDate = sdf.format(timeStamp);

    public static String getFormattedDate() {
        return formattedDate;
    }
}
