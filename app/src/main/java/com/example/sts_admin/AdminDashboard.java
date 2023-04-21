package com.example.sts_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sts_admin.sharedpref.SharedPrefManager;

public class AdminDashboard extends AppCompatActivity implements View.OnClickListener{

    CardView update,user,trip;

    TextView tvUsername, tvEmail;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        update=(CardView) findViewById(R.id.updateSch);
        user=(CardView) findViewById(R.id.user_profile);
        trip=(CardView) findViewById(R.id.trip);



        update.setOnClickListener((View.OnClickListener) this);
        user.setOnClickListener((View.OnClickListener) this);
        trip.setOnClickListener((View.OnClickListener)this);

        // call the user details methods
        initializeViews();
        getLoggedInUserDetails();

//        findViewById(R.id.card1).setOnClickListener(v->startActivity(new Intent(Cards.this,LoginActivity.class)));
//        findViewById(R.id.card2).setOnClickListener(v->startActivity(new Intent(Cards.this,LoginActivity.class)));

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