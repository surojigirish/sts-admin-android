package com.example.sts_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sts_admin.apiservice.ApiClient;
import com.example.sts_admin.logoutModel.LogoutRequest;
import com.example.sts_admin.logoutModel.LogoutResponse;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDashboard extends AppCompatActivity implements View.OnClickListener{

    CardView update,user,trip;

    TextView tvUsername, tvEmail;

    Button logoutBtn;
    Button viewDrivers;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        update=(CardView) findViewById(R.id.updateSch);
        user=(CardView) findViewById(R.id.user_profile);
        trip=(CardView) findViewById(R.id.trip);
        logoutBtn=findViewById(R.id.logoutBtn);

        viewDrivers = findViewById(R.id.btn_view_drivers);



        update.setOnClickListener((View.OnClickListener) this);
        user.setOnClickListener((View.OnClickListener) this);
        trip.setOnClickListener((View.OnClickListener)this);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(logoutRequest());

            }
        });



        viewDrivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivityOnClick();
            }
        });

        // call the user details methods
//        initializeViews();
//        getLoggedInUserDetails();

//        findViewById(R.id.card1).setOnClickListener(v->startActivity(new Intent(Cards.this,LoginActivity.class)));
//        findViewById(R.id.card2).setOnClickListener(v->startActivity(new Intent(Cards.this,LoginActivity.class)));
//
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.logoutmenu,menu);
//        return super.onCreateOptionsMenu(menu);
//
//    }



    public LogoutRequest logoutRequest(){
       LogoutRequest logoutRequest=new LogoutRequest();
       logoutRequest.setToken(getSessionToken());
       return logoutRequest;
    }



    public void logout(LogoutRequest logoutRequest){
        Call<LogoutResponse> logoutResponseCall= ApiClient.getRoute().logout(logoutRequest);
        logoutResponseCall.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                LogoutResponse logoutResponse=response.body();
                if (response.isSuccessful()){
//                    Toast.makeText(AdminDashboard.this, "Logout successful", Toast.LENGTH_SHORT).show();
                    if(logoutResponse != null && logoutResponse.getStatus() == 200){
                        sharedPrefManager.logout();
                        Toast.makeText(AdminDashboard.this, "Logout successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(AdminDashboard.this,AdminLogin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    Toast.makeText(AdminDashboard.this, "onResponse: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Toast.makeText(AdminDashboard.this, "onFailure: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v){
        Intent i;
        switch (v.getId()){
            case R.id.updateSch:i=new Intent(this, UpdateBusSchedule.class);startActivity(i);break;
            case R.id.user_profile:i=new Intent(this, DriverRegistration.class);startActivity(i);break;
            case R.id.trip:i=new Intent(this, TripHistory.class);startActivity(i);break;

        }
    }

//    public void getLoggedInUserDetails() {
//        sharedPrefManager = new SharedPrefManager(getApplicationContext());
//
//        String username = "Welcome back! "
//                + sharedPrefManager.getUser().getFirstname()
//                + " "
//                + sharedPrefManager.getUser().getLastname();
//
//        tvUsername.setText(username);
//        tvEmail.setText(sharedPrefManager.getUser().getEmail());
//    }

    // required for logout
    public String getSessionToken() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        return sharedPrefManager.getUser().getToken();
    }

    public void switchActivityOnClick(){
        Intent intent=new Intent(AdminDashboard.this, DriverDetails.class);
        startActivity(intent);
    }
//
//    void initializeViews() {
//        tvUsername = findViewById(R.id.tv_username);
//        tvEmail = findViewById(R.id.tv_email);
//    }
}