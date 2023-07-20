package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.UpdateScheduleRequest;
import com.example.sts_admin.apiservice.response.UpdateScheduleResponse;
import com.example.sts_admin.assets.TimeDurationCalculator;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateScheduleData extends AppCompatActivity {

    TextView title,tvRoute, tvDepartureTime, tvArrivalTime, tvDuration;
    AppCompatButton updateScheduleBtn;
    AppCompatImageButton backButton;
    int hour, minute;
    String SavaDepartureTime,SavaArrivalTime;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_schedule_data);
        initViews();


    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Integer scheduleId = Integer.valueOf(intent.getStringExtra("sId"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    intent = new Intent(getApplicationContext(), AddSchedule.class);
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        // departure time setter
        tvDepartureTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departureTimeFunc();
            }
        });

        // arrival time setter
        tvArrivalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrivalTimeFunc();
            }

        });

        // method to calculate time duration
        tvDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateDuration();
                Log.i("TAG", "onStart: calculate duration called");

            }
        });


        updateScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScheduleData(updateScheduleRequest(),scheduleId);
            }
        });

    }


    // =================== initialize views===================
    public void initViews(){
        tvRoute = findViewById(R.id.tvupdaterouteId);
        tvDepartureTime = findViewById(R.id.et_update_departure);
        tvArrivalTime = findViewById(R.id.et_update_arrival);
        tvDuration = findViewById(R.id.et_update_duration);
        updateScheduleBtn = findViewById(R.id.updateScheduleBtn);
        backButton = findViewById(R.id.back_btn_update_schedule_screen);

        Intent intent = getIntent();
        String routeName = intent.getStringExtra("Ssource")+ " To "+intent.getStringExtra("Sdestination");
        String arrival = intent.getStringExtra("SarrivalAt");
        String departure = intent.getStringExtra("SdepartureAt");
        String duration = intent.getStringExtra("Sduration");

        tvRoute.setText(routeName);
        tvArrivalTime.setText(arrival);
        tvDepartureTime.setText(departure);
        tvDuration.setText(duration);
    }

    // ================== calculate time ======================
    public void departureTimeFunc() {
        // Button click listener

        // Get the current time
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        // Create a TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                UpdateScheduleData.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        // Store the selected time
                        hour = selectedHour;
                        minute = selectedMinute;

                        // Update your UI or perform any required action with the selected time
                        // For example, you can display the selected time in a TextView
                        setSavaDepartureTime(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                        Log.i("TAG", "onTimeSet: departure time is : "+getSavaDepartureTime());
                        tvDepartureTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                    }
                },
                hour,
                minute,
                false
        );
        // Show the TimePickerDialog
        timePickerDialog.show();
    }

    public void arrivalTimeFunc() {
        // Create a TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                UpdateScheduleData.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        // Store the selected time
                        hour = selectedHour;
                        minute = selectedMinute;

                        // Update your UI or perform any required action with the selected time
                        // For example, you can display the selected time in a TextView
                        setSavaArrivalTime(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                        Log.i("TAG", "onTimeSet: arrival time is : "+getSavaArrivalTime());
                        tvArrivalTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                    }
                },
                hour,
                minute,
                false
        );
        // Show the TimePickerDialog
        timePickerDialog.show();
    }

    public void calculateDuration() {
        String duration = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            duration = TimeDurationCalculator.calculateDuration(getSavaDepartureTime(), getSavaArrivalTime());
        }
        Log.i("TAG", "onCreate: " + duration);
        tvDuration.setText(duration);
    }


    // ======================== API call =======================


    public UpdateScheduleRequest updateScheduleRequest(){
        UpdateScheduleRequest scheduleRequest=new UpdateScheduleRequest();
        scheduleRequest.setUpdate_departure_at(getSavaDepartureTime());
        Log.i("TAG", "scheduleRequest: depart time" + getSavaDepartureTime());
        scheduleRequest.setUpdate_arrival_at(getSavaArrivalTime());
        scheduleRequest.setUpdate_duration(tvDuration.getText().toString());
        return scheduleRequest;
    }


    public void updateScheduleData(UpdateScheduleRequest updateScheduleRequest, Integer scheduleId){

        Call<UpdateScheduleResponse> call = Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().updateScheduleDetails(scheduleId,updateScheduleRequest);
        call.enqueue(new Callback<UpdateScheduleResponse>() {
            @Override
            public void onResponse(Call<UpdateScheduleResponse> call, Response<UpdateScheduleResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(UpdateScheduleData.this, "Schedule Updated Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        intent = new Intent(getApplicationContext(), AddSchedule.class);
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(UpdateScheduleData.this, "fail to connect to db", Toast.LENGTH_LONG).show();
                    Log.i("TAG", "onFailure: ");
                    Toast.makeText(UpdateScheduleData.this, "onresponse failiar", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateScheduleResponse> call, Throwable t) {
                Toast.makeText(UpdateScheduleData.this, "fail to connect to db", Toast.LENGTH_LONG).show();
                Log.i("TAG", "onFailure: "+t.getLocalizedMessage());
            }
        });

    }


    // ================== user session token =================

    public String getUserSession() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        return sharedPrefManager.getUser().getToken();
    }




    //==================== getter setter=======================

    public String getSavaDepartureTime() {
        return SavaDepartureTime;
    }

    public void setSavaDepartureTime(String savaDepartureTime) {
        SavaDepartureTime = savaDepartureTime;
    }

    public String getSavaArrivalTime() {
        return SavaArrivalTime;
    }

    public void setSavaArrivalTime(String savaArrivalTime) {
        SavaArrivalTime = savaArrivalTime;
    }
}