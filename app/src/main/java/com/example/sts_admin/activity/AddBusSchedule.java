package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.AddBusScheduleRequest;
import com.example.sts_admin.apiservice.response.BusScheduleResponse;
import com.example.sts_admin.fragments.SearchBusId;
import com.example.sts_admin.fragments.SearchDriver;
import com.example.sts_admin.fragments.SearchScheduleId;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBusSchedule extends AppCompatActivity {

    String selectedDate;
    Integer scheduleId,busId;

    TextView title,datePicker;
    private int year, month, dayOfMonth;
    TextView etBusid,etScheduleId,tvDriver;
    Button addBusScheduleBtn;

    // shared pref
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus_schedule);

        initComponents();

        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        etBusid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchBusId frag = new SearchBusId();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout_busSchedule, frag);
                transaction.commit();

                hideViewsOnFragTransaction();
            }
        });
        getSearchBusData();

        etScheduleId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchScheduleId frag = new SearchScheduleId();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout_busSchedule, frag);
                transaction.commit();

                hideViewsOnFragTransaction();
            }
        });
        getSearchScheduleData();

        tvDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchDriver frag = new SearchDriver();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout_busSchedule,frag);
                transaction.addToBackStack(null);
                transaction.commit();

                Log.i("TAG", "onClick: Driver Search Fragment opened" );

                hideViewsOnFragTransaction();
            }
        });


        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentDate();
            }
        });


    }// on create end braces

    @Override
    protected void onStart() {
        super.onStart();

        showTextViewData();
        addBusScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBusSchedules(addBusSchedule());
                sharedPrefManager.clearScheduleData();
                clearViews();
            }
        });
    }

    private void clearViews(){
        tvDriver.setText("");
        etBusid.setText("");
        etScheduleId.setText("");
    }



    public AddBusScheduleRequest addBusSchedule(){
        AddBusScheduleRequest addBusScheduleRequest = new AddBusScheduleRequest();
        addBusScheduleRequest.setBusId(sharedPrefManager.getBusDetails().getId());
        Log.i("TAG", "addBusSchedule: bus "+sharedPrefManager.getBusDetails().getRegNo());
        addBusScheduleRequest.setScheduleId(sharedPrefManager.getScheduleDetails().getId());

        Log.i("TAG", "addBusSchedule: schedule "+sharedPrefManager.getScheduleDetails().getId());
        addBusScheduleRequest.setDriverid(sharedPrefManager.getDriverDetails().getEmpId());
        Log.i("TAG", "addBusSchedule: driver"+sharedPrefManager.getDriverDetails().getEmpId());

        addBusScheduleRequest.setDate(getSelectedDate());
        Log.i("TAG", "addBusSchedule: date "+getSelectedDate());
        return addBusScheduleRequest;
    }

    public void AddBusSchedules(AddBusScheduleRequest addBusScheduleRequest){
        Call<BusScheduleResponse> busScheduleResponseCall = Client.getInstance(Consts.BASE_URL_BUS).getRoute().addBusSchedule(addBusScheduleRequest);
        busScheduleResponseCall.enqueue(new Callback<BusScheduleResponse>() {
            @Override
            public void onResponse(Call<BusScheduleResponse> call, Response<BusScheduleResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    Log.i("TAG", "onResponse: success "+response.body());
                    Toast.makeText(AddBusSchedule.this, "Bus scheduled successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddBusSchedule.this, AdminDashboard.class);
//                    // setFlags clears previous tasks
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
//                    finish();
                }
            }

            @Override
            public void onFailure(Call<BusScheduleResponse> call, Throwable t) {
                Toast.makeText(AddBusSchedule.this, "Failed to schedule", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "onFailure: " +t.getLocalizedMessage());
            }
        });
    }

    public void showTextViewData() {
        String busRegNo = sharedPrefManager.getBusDetails().getRegNo();
        String schedule = sharedPrefManager.getScheduleDetails().getRouteSource() + " to " + sharedPrefManager.getScheduleDetails().getRouteDestination();
        String driver = sharedPrefManager.getDriverDetails().getFirstname() + " " + sharedPrefManager.getDriverDetails().getLastname();

        if (busRegNo.isEmpty()) {
            busRegNo = "SELECT BUS";
        }

        etBusid.setText(busRegNo);
        etScheduleId.setText(schedule);
        tvDriver.setText(driver);
    }


    public void getCurrentDate(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                AddBusSchedule.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       setSelectedDate(year + "-" + (month + 1) + "-" + dayOfMonth);
                        datePicker.setText("Scheduler on : "+selectedDate);
                    }
                },
                year,
                month,
                dayOfMonth
        );
        datePickerDialog.show();
    }

    public void initComponents(){
        title = findViewById(R.id.tv_bus_schedule_title);
        etBusid = findViewById(R.id.et_bus_id);
        etScheduleId = findViewById(R.id.et_schedule_id);
        datePicker = findViewById(R.id.tv_date_picker);
        addBusScheduleBtn = findViewById(R.id.add_bus_details_btn);
        tvDriver = findViewById(R.id.et_driver_id);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void hideViewsOnFragTransaction() {
        // Hide the views in the current activity
        title.setVisibility(View.GONE);
        etBusid.setVisibility(View.GONE);
        etScheduleId.setVisibility(View.GONE);
        datePicker.setVisibility(View.GONE);
        addBusScheduleBtn.setVisibility(View.GONE);
        tvDriver.setVisibility(View.GONE);
    }

    public void getSearchBusData(){
        Intent i = getIntent();
        setBusId(i.getIntExtra("scheduleId",0));
        String busRegNo = i.getStringExtra("busRegNo");

        etBusid.setText("Bus reg no. :"+busRegNo);
    }
    public void getSearchScheduleData(){
        Intent i = getIntent();
        setScheduleId(i.getIntExtra("scheduleId",0));
        String  scheduleSource = i.getStringExtra("scheduleSource");
        String  scheduleDest = i.getStringExtra("scheduleDestination");
        String srcdest = scheduleSource+" to "+ scheduleDest;

        etScheduleId.setText("Scheduled at : "+srcdest);
    }

    // getter and setter
    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }
}