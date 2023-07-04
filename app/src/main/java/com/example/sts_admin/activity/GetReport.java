package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.BusReportAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.ReportGenerationResponse;
import com.example.sts_admin.fragments.GetBusId;
import com.example.sts_admin.model.ScheduleR;
import com.example.sts_admin.model.results.ResultReport;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetReport extends AppCompatActivity {

    TextView title, tvDate,tvBusId, tvReportDate, tvBusRegNumber, tvBusType;
    AppCompatButton reportBtn;
    RecyclerView rvReportList;
    String selectedDate;
    Integer busId;
    private int year, month, dayOfMonth;
    SharedPrefManager sharedPrefManager;
    List<ScheduleR> resultReportList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_report);
        initComponents();

        rvReportList.setHasFixedSize(true);
        rvReportList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentDate();
            }
        });

        tvBusId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetBusId frag = new GetBusId();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout_Report, frag);
                transaction.commit();

                hideViewsOnFragTransaction();
            }
        });
        getSearchBusData();
        Log.i("TAG", "onCreate: "+getBusId());


        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewReport(getBusId(), getSelectedDate());
            }
        });


    }

    private void ViewReport(Integer busId, String selectedDate) {
        Call<ReportGenerationResponse> responseCall = Client.getInstance(Consts.BASE_URL_REPORT).getRoute().getBusReport(busId,selectedDate);
        responseCall.enqueue(new Callback<ReportGenerationResponse>() {
            @Override
            public void onResponse(Call<ReportGenerationResponse> call, Response<ReportGenerationResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    // Api bus data
                    String busRegistrationNumber = response.body().getResult().getBusR().getRegNo();
                    String busType = response.body().getResult().getBusR().getType();
                    String reportDate = response.body().getResult().getDate();
                    // Display the bus and report date on text views
                    tvReportDate.setText(reportDate);
                    tvBusRegNumber.setText(busRegistrationNumber);
                    tvBusType.setText(busType);

                    resultReportList = response.body().getResult().getScheduleR();
                    rvReportList.setAdapter(new BusReportAdapter(resultReportList, getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<ReportGenerationResponse> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

//        showTextViewData();
    }

    public void initComponents(){
        title = findViewById(R.id.tvReportTitle);
        tvBusId = findViewById(R.id.tv_report_bus_id);
        tvDate = findViewById(R.id.tv_report_date_picker);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        reportBtn = findViewById(R.id.generateReportBtn);
        rvReportList = findViewById(R.id.rvReport);

        // TextView to hold dynamic bus detail for report
        tvReportDate = findViewById(R.id.tv_report_date);
        tvBusRegNumber = findViewById(R.id.tv_report_busRegNumber);
        tvBusType = findViewById(R.id.tv_report_busType);
    }


    public void getCurrentDate(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                GetReport.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        setSelectedDate(year + "-" + (month + 1) + "-" + dayOfMonth);
                        tvDate.setText(""+selectedDate);
                        Log.i("TAG", "onDateSet: " +selectedDate);
                    }
                },
                year,
                month,
                dayOfMonth
        );
        datePickerDialog.show();
    }


    public void hideViewsOnFragTransaction() {
        // Hide the views in the current activity
        title.setVisibility(View.GONE);
        reportBtn.setVisibility(View.GONE);
        tvBusId.setVisibility(View.GONE);
        tvDate.setVisibility(View.GONE);
        rvReportList.setVisibility(View.GONE);


    }

    public void getSearchBusData(){
        Intent i = getIntent();
        setBusId(i.getIntExtra("busId",0));
        String busRegNo = i.getStringExtra("busRegNo");

        tvBusId.setText(""+busRegNo);
    }

    public void showTextViewData() {
        String busRegNo = sharedPrefManager.getBusDetails().getRegNo();

        if (busRegNo.isEmpty()) {
            busRegNo = "SELECT BUS";
        }

        tvBusId.setText(busRegNo);

    }




    // getter && setter
    public String getSelectedDate() {
        return selectedDate;
    }
    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }
}