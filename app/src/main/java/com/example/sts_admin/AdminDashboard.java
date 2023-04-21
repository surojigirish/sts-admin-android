package com.example.sts_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminDashboard extends AppCompatActivity implements View.OnClickListener{

    CardView update,user,trip;

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
}