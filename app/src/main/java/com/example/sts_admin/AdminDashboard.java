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

import com.example.sts_admin.apiclient.ApiClient;
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
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        update=(CardView) findViewById(R.id.updateSch);
        user=(CardView) findViewById(R.id.user_profile);
        trip=(CardView) findViewById(R.id.trip);
        logoutBtn=findViewById(R.id.logoutBtn);



        update.setOnClickListener((View.OnClickListener) this);
        user.setOnClickListener((View.OnClickListener) this);
        trip.setOnClickListener((View.OnClickListener)this);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(logoutRequest());

            }
        });

        // call the user details methods
        initializeViews();
        getLoggedInUserDetails();

//        findViewById(R.id.card1).setOnClickListener(v->startActivity(new Intent(Cards.this,LoginActivity.class)));
//        findViewById(R.id.card2).setOnClickListener(v->startActivity(new Intent(Cards.this,LoginActivity.class)));

    }

    public LogoutRequest logoutRequest(){
       LogoutRequest logoutRequest=new LogoutRequest();
       logoutRequest.setToken(sharedPrefManager.getUser().getToken());
       return logoutRequest;
    }

    public void logout(LogoutRequest logoutRequest){
        Call<LogoutResponse> logoutResponseCall= ApiClient.getLogoutAdminRoute().adminLogout(logoutRequest);
        logoutResponseCall.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                LogoutResponse logoutResponse=response.body();
                if (response.isSuccessful()){
                    if(logoutResponse!=null && logoutResponse.getStatus()==200){
                        sharedPrefManager.logout();
                        Toast.makeText(AdminDashboard.this, "Logout successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(AdminDashboard.this,AdminLogin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }

                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {

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

    public void getLoggedInUserDetails() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        String username = "Welcome back! "
                + sharedPrefManager.getUser().getFirstname()
                + " "
                + sharedPrefManager.getUser().getLastname();

        tvUsername.setText(username);
        tvEmail.setText(sharedPrefManager.getUser().getEmail());
    }

    void initializeViews() {
        tvUsername = findViewById(R.id.tv_username);
        tvEmail = findViewById(R.id.tv_email);
    }
}