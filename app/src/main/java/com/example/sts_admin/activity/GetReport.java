package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.BusReportAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.ReportGenerationResponse;
import com.example.sts_admin.fragments.GetBusId;
import com.example.sts_admin.model.ScheduleR;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetReport extends AppCompatActivity {

    TextView title, tvDate,tvBusId, tvReportDate, tvBusRegNumber, tvBusType, busNo ,dateEnd;
    AppCompatButton reportBtn;
    AppCompatImageButton backButton;
    AppCompatImageView no_schedule_data_image;
    RecyclerView rvReportList;
    String selectedDate,endSelectedDate;
    Integer busId;
    View v8,v9,v10;
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
                getUserSelectedDate(tvDate);
                setSelectedDate(year + "-" + (month + 1) + "-" + dayOfMonth);
                Log.i("TAG", "date onClick: "+getSelectedDate());
            }
        });

        dateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserSelectedDate(dateEnd);
                setEndSelectedDate(year + "-" + (month + 1) + "-" + dayOfMonth);
                Log.i("TAG", "end date onClick: "+getEndSelectedDate());
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
                ViewReport(getBusId(), getSelectedDate(),getEndSelectedDate());
            }
        });


    }

    private void ViewReport(Integer busId, String selectedDate, String endSelectedDate) {
        Log.i("TAG", "ViewReport: startdate "+selectedDate);
        Log.i("TAG", "ViewReport: enddate "+endSelectedDate);
        Call<ReportGenerationResponse> responseCall = Client.getInstance(Consts.BASE_URL_REPORT).getRoute().getBusReport(busId,selectedDate);
        responseCall.enqueue(new Callback<ReportGenerationResponse>() {
            @Override
            public void onResponse(Call<ReportGenerationResponse> call, Response<ReportGenerationResponse> response) {
                if (response.isSuccessful() && response.body() != null){

                    // Api bus data
                    String busRegistrationNumber = "Bus Number : " + response.body().getResult().getBusR().getRegNo();
                    String busType = "Bus Type : " + response.body().getResult().getBusR().getType();
                    String reportDate = "Date : " + response.body().getResult().getDate();
                    // Display the bus and report date on text views
                    tvReportDate.setText(reportDate);
                    tvBusRegNumber.setText(busRegistrationNumber);
                    tvBusType.setText(busType);

                    resultReportList = response.body().getResult().getScheduleR();


                     // ui changes if not bus schedule available
                    if (resultReportList.isEmpty()){
                        rvReportList.setVisibility(View.GONE);
                        no_schedule_data_image.setVisibility(View.VISIBLE);

                        // Use Glide to load the image into the ImageView
                        Glide.with(getApplicationContext())
                                .load(R.drawable.no_results)
                                .into(no_schedule_data_image);
                    }else {
                        no_schedule_data_image.setVisibility(View.GONE);
                        rvReportList.setAdapter(new BusReportAdapter(resultReportList, getApplicationContext()));
                    }

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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
//        showTextViewData();
    }

    public void initComponents(){
        title = findViewById(R.id.tvReportTitle);
        tvBusId = findViewById(R.id.tv_report_bus_id);
        tvDate = findViewById(R.id.tv_report_date_picker);
        no_schedule_data_image = findViewById(R.id.no_data);
        dateEnd = findViewById(R.id.tv_report_date_picker_last);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        reportBtn = findViewById(R.id.generateReportBtn);
        rvReportList = findViewById(R.id.rvReport);
        backButton = findViewById(R.id.back_btn_passenger_details_screen);

        // TextView to hold dynamic bus detail for report
        tvReportDate = findViewById(R.id.tv_report_date);
        tvBusRegNumber = findViewById(R.id.tv_report_busRegNumber);
        tvBusType = findViewById(R.id.tv_report_busType);
        busNo = findViewById(R.id.tvbusno);
        v8 = findViewById(R.id.view8);
        v9 = findViewById(R.id.view9);
        v10 = findViewById(R.id.view10);
    }


    public void getUserSelectedDate(TextView Date){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                GetReport.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        setSelectedDate(year + "-" + (month + 1) + "-" + dayOfMonth);
//                        tvDate.setText(""+selectedDate);
                        Date.setText(""+selectedDate);
//                        Log.i("TAG", "onDateSet: " +selectedDate);
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
        dateEnd.setVisibility(View.GONE);
        rvReportList.setVisibility(View.GONE);
        tvReportDate.setVisibility(View.GONE);
        tvBusType.setVisibility(View.GONE);
        tvBusRegNumber.setVisibility(View.GONE);
        busNo.setVisibility(View.GONE);
        v8.setVisibility(View.GONE);
        v9.setVisibility(View.GONE);
        v10.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);


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

    public String getEndSelectedDate() {
        return endSelectedDate;
    }

    public void setEndSelectedDate(String endSelectedDate) {
        this.endSelectedDate = endSelectedDate;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }
}