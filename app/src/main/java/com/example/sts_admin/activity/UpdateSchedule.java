package com.example.sts_admin.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sts_admin.R;

public class UpdateSchedule extends AppCompatActivity {


    Button addRoute,addSource,addSchedule;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_schedule);

//        text=findViewById(R.id.updateText);
//        schedule=findViewById(R.id.schedule);
//        departure=findViewById(R.id.departure);
//        duration=findViewById(R.id.duration);
//        arrive=findViewById(R.id.arrive);
//        route=findViewById(R.id.route);
//        updateBtn=findViewById(R.id.updateButton);

        addRoute=findViewById(R.id.add_route);
        addSource=findViewById(R.id.add_source);
        addSchedule=findViewById(R.id.add_schedule);


       addRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UpdateSchedule.this, AddRoute.class);
                startActivity(intent);
            }
        });

       addSource.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(UpdateSchedule.this, AddHalts.class);
               startActivity(intent);
           }
       });

       addSchedule.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.N)
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(UpdateSchedule.this, AddSchedule.class);
               startActivity(intent);
           }
       });
    }
}