package com.example.sts_admin;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

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
}
