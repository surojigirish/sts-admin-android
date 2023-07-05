package com.example.sts_admin.activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.ShowRouteAdapter;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.ScheduleRequest;
import com.example.sts_admin.apiservice.response.GetRouteResponse;
import com.example.sts_admin.apiservice.response.RouteScheduleResponse;
import com.example.sts_admin.apiservice.response.ScheduleResponse;
import com.example.sts_admin.assets.TimeDurationCalculator;
import com.example.sts_admin.fragments.SearchRouteId;
import com.example.sts_admin.model.AddRouteDetails;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AddSchedule extends AppCompatActivity {
    Integer routeIdInfo;
    TextView departureTime,arrivalTime,durationTime, tv_routeId,text,textView5;

    // ImageView holding screen bg
    ImageView imageViewScreenBackground;
    List<AddRouteDetails> addRouteDetailsList;

    ShowRouteAdapter.OnRouteItemClickListener onRouteItemClickListener;

    AppCompatButton addScheduleBtn;

    String saveArrivalTime, savaDepartureTime, timeDuration;
    RecyclerView rvShowRoutes;

    // Declare global variables
    int hour, minute;

    SharedPrefManager sharedPrefManager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

//        imageViewScreenBackground = findViewById(R.id.imgView_screen_bg);

        // Load the svg here
//        Glide.with(this)
//                .load(R.drawable.screen_bg_sts)
//                .into(imageViewScreenBackground);


        departureTime=findViewById(R.id.et_departure);
        arrivalTime=findViewById(R.id.et_arrival);
        addScheduleBtn=findViewById(R.id.addScheduleBtn);

        durationTime=findViewById(R.id.et_duration);
        tv_routeId =findViewById(R.id.tvrouteId);
        text=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);

        rvShowRoutes = findViewById(R.id.rvShowRoutesForSchedule);
        rvShowRoutes.setHasFixedSize(true);
        rvShowRoutes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        sharedPrefManager=new SharedPrefManager(getApplicationContext());

//        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.class.getModifiers(), android.R.layout.simple_dropdown_item_1line);

    }

    @Override
    protected void onStart() {
        super.onStart();

        getRouteList();
        // departure time setter
        departureTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departureTimeFunc();
            }
        });

        // arrival time setter
        arrivalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrivalTimeFunc();
            }

        });


        // method to calculate time duration

        durationTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculateDuration();
                Log.i("TAG", "onStart: calculate duration called");

            }
        });



        addScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String route = tv_routeId.getText().toString().trim();
                String departure = departureTime.getText().toString().trim();
                String arrival = arrivalTime.getText().toString().trim();
                String duration = durationTime.getText().toString().trim();

                if (route.isEmpty()) {
                    tv_routeId.setError("Required");
                } else if (departure.isEmpty()) {
                    tv_routeId.setError(null);
                    departureTime.setError("Required");
                } else if (arrival.isEmpty()){
                    departureTime.setError(null);
                    arrivalTime.setError("Required");
                }else if (duration.isEmpty()){
                    arrivalTime.setError(null);
                    durationTime.setError("Required");
                }else {
                    durationTime.setError(null);
                    schedule(scheduleRequest());
                }


            }
        });


        tv_routeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchRouteId searchRouteId = new SearchRouteId();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_routeId,searchRouteId);
                transaction.commit();

                hideViews();
            }
        });
        getRouteInfo();

        onRouteItemClickListener = new ShowRouteAdapter.OnRouteItemClickListener() {
            @Override
            public void onClickListener(Integer routeId, String routeSource, String routeDest) {

            }
        };

    }



    /*-- used to get all the routes and display schedule allocated to each route-----*/
    private void getRouteList() {
        Call<GetRouteResponse> getRouteResponseCall = Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().getAllRouteList();

        getRouteResponseCall.enqueue(new Callback<GetRouteResponse>() {
            @Override
            public void onResponse(Call<GetRouteResponse> call, Response<GetRouteResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        addRouteDetailsList = response.body().getAddRouteDetailsList();

                        // Setting Adapter
                        rvShowRoutes.setAdapter(new ShowRouteAdapter(addRouteDetailsList, getApplicationContext(), new ShowRouteAdapter.OnRouteItemClickListener() {
                            @Override
                            public void onClickListener(Integer routeId, String routeS,String routeD) {
                                Intent i = new Intent(getApplicationContext(), ShowRouteBasedSchedule.class);
//                                sharedPrefManager.saveRoute(routeId);
                                i.putExtra("routeId", routeId);
                                i.putExtra("routeSource", routeS);
                                i.putExtra("routeDest", routeD);
                                startActivity(i);
                            }
                        }));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetRouteResponse> call, Throwable t) {

            }
        });


    }

    public void hideViews(){
        departureTime.setVisibility(View.GONE);
        arrivalTime.setVisibility(View.GONE);
        durationTime.setVisibility(View.GONE);
        tv_routeId.setVisibility(View.GONE);
        addScheduleBtn.setVisibility(View.GONE);
        text.setVisibility(View.GONE);
        textView5.setVisibility(View.GONE);
        rvShowRoutes.setVisibility(View.GONE);
//        imageViewScreenBackground.setVisibility(View.GONE);
    }

    public void calculateDuration() {
        String duration = TimeDurationCalculator.calculateDuration(getSavaDepartureTime(), getSaveArrivalTime());
        Log.i("TAG", "onCreate: " + duration);
        durationTime.setText(duration);
    }

    public void departureTimeFunc() {
        // Button click listener

        // Get the current time
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        // Create a TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                AddSchedule.this,
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
                        departureTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
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
                AddSchedule.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        // Store the selected time
                        hour = selectedHour;
                        minute = selectedMinute;

                        // Update your UI or perform any required action with the selected time
                        // For example, you can display the selected time in a TextView
                        setSaveArrivalTime(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                        Log.i("TAG", "onTimeSet: arrival time is : "+getSaveArrivalTime());
                        arrivalTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                    }
                },
                hour,
                minute,
                false
        );
        // Show the TimePickerDialog
        timePickerDialog.show();
    }

    public void getRouteInfo(){
        Intent i= getIntent();
        setRouteIdInfo(i.getIntExtra("routeId",0));
        String source=i.getStringExtra("source");
        String destination=i.getStringExtra("destination");
        String tvSrcDst = source +" to "+destination;
        tv_routeId.setText( tvSrcDst);

//        if (source.equals(null) && destination.equals(null)){
//            tv_routeId.setText("add route");
//        }else {

//        }
    }


    public ScheduleRequest scheduleRequest(){
        ScheduleRequest scheduleRequest=new ScheduleRequest();
        scheduleRequest.setDepartureTime(getSavaDepartureTime());
        Log.i("TAG", "scheduleRequest: depart time" + getSavaDepartureTime());
        scheduleRequest.setArrivalTime(getSaveArrivalTime());
        scheduleRequest.setDuration(durationTime.getText().toString());
        scheduleRequest.setRouteId(getRouteIdInfo());
        return scheduleRequest;
    }

    public void schedule(ScheduleRequest scheduleRequest){
        Call<ScheduleResponse>scheduleResponseCall= Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().addSchedule(scheduleRequest);
        scheduleResponseCall.enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                ScheduleResponse scheduleResponse= response.body();
                if (response.isSuccessful()){
                    if(scheduleResponse!=null && scheduleResponse.getStatus() == 200 ){
                        Toast.makeText(AddSchedule.this, "Schedule Added", Toast.LENGTH_LONG).show();


                    }
                } else {
                    Toast.makeText(AddSchedule.this, "Schedule Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                Toast.makeText(AddSchedule.this, "fail to connect to db", Toast.LENGTH_LONG).show();
                Log.i("TAG", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }




    // getter setter of arrive and departure time
    public String getSaveArrivalTime() {
        return saveArrivalTime;
    }

    public void setSaveArrivalTime(String saveArrivalTime) {
        this.saveArrivalTime = saveArrivalTime;
    }

    public String getSavaDepartureTime() {
        return savaDepartureTime;
    }

    public void setSavaDepartureTime(String savaDepartureTime) {
        this.savaDepartureTime = savaDepartureTime;
    }

    public Integer getRouteIdInfo() {
        return routeIdInfo;
    }

    public void setRouteIdInfo(Integer routeIdInfo) {
        this.routeIdInfo = routeIdInfo;
    }
}