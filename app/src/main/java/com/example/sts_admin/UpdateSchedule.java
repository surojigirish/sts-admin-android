package com.example.sts_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateSchedule extends AppCompatActivity {


    Button addRoute,addSource;

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
               Intent intent=new Intent(UpdateSchedule.this, AddSource.class);
               startActivity(intent);
           }
       });
    }
}