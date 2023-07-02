package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.AdminLogoutRequest;
import com.example.sts_admin.apiservice.response.AdminLogoutResponse;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDashboard extends AppCompatActivity implements View.OnClickListener{

    CardView update,user, addBusSchedule,bus;

    TextView tvUsername, tvEmail;

    AppCompatButton logoutBtn, getPassengerDetailsBtn;
//    Button viewDrivers;
    SharedPrefManager sharedPrefManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        update=(CardView) findViewById(R.id.updateSch);
        user=(CardView) findViewById(R.id.user_profile);
        addBusSchedule =(CardView) findViewById(R.id.trip);
        bus=(CardView) findViewById(R.id.bus);
        logoutBtn=findViewById(R.id.logoutBtn);
        getPassengerDetailsBtn = findViewById(R.id.getPassengerDetailsBtn);

//        viewDrivers = findViewById(R.id.btn_view_drivers);



        update.setOnClickListener((View.OnClickListener) this);
        user.setOnClickListener((View.OnClickListener) this);
        addBusSchedule.setOnClickListener((View.OnClickListener)this);
        bus.setOnClickListener((View.OnClickListener)this);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(logoutRequest());
            }
        });

        getPassengerDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminDashboard.this,GetPassengerDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });
    }




    public AdminLogoutRequest logoutRequest(){
       AdminLogoutRequest logoutRequest=new AdminLogoutRequest();
       logoutRequest.setToken(getSessionToken());
       return logoutRequest;
    }




    public void logout(AdminLogoutRequest logoutRequest){
        Call<AdminLogoutResponse> logoutResponseCall= Client.getInstance(Consts.BASE_URL_ADMIN).getRoute().logout(logoutRequest);
        logoutResponseCall.enqueue(new Callback<AdminLogoutResponse>() {
            @Override
            public void onResponse(Call<AdminLogoutResponse> call, Response<AdminLogoutResponse> response) {
                AdminLogoutResponse logoutResponse=response.body();
                if (response.isSuccessful()){
                    if(logoutResponse != null && logoutResponse.getStatus() == 200){
                        sharedPrefManager.logout();
                        Toast.makeText(AdminDashboard.this, "Logout successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(AdminDashboard.this, Welcome.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    Toast.makeText(AdminDashboard.this, "onResponse: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AdminLogoutResponse> call, Throwable t) {
                Toast.makeText(AdminDashboard.this, "onFailure: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v){
        Intent i;
        switch (v.getId()){
            case R.id.updateSch:i=new Intent(this, UpdateSchedule.class);startActivity(i);break;
            case R.id.user_profile:i=new Intent(this, DriverRegistration.class);startActivity(i);break;
            case R.id.trip:i=new Intent(this, AddBusSchedule.class);startActivity(i);break;
            case R.id.bus:i=new Intent(this, AddBusDetails.class);startActivity(i);break;

        }
    }
    public String getSessionToken() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        return sharedPrefManager.getUser().getToken();
    }
}