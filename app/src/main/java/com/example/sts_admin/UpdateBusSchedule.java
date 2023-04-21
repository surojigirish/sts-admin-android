package com.example.sts_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateBusSchedule extends AppCompatActivity {

    TextView text;
    EditText schedule,departure,duration,arrive,route;
    Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bus_schedule);

        text=findViewById(R.id.updateText);
        schedule=findViewById(R.id.schedule);
        departure=findViewById(R.id.departure);
        duration=findViewById(R.id.duration);
        arrive=findViewById(R.id.arrive);
        route=findViewById(R.id.route);
        updateBtn=findViewById(R.id.updateButton);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateBusSchedule.this, "Update Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}