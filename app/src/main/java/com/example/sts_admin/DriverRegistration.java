package com.example.sts_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DriverRegistration extends AppCompatActivity {

    TextView text;
    EditText name,email,username,password;
    Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registartion);


        text=findViewById(R.id.driverText);
        name=findViewById(R.id.driverName);
        email=findViewById(R.id.driverEmail);
        username=findViewById(R.id.driverUsername);
        password=findViewById(R.id.driverPassword);
        regBtn=findViewById(R.id.driverRegBtn);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(DriverRegistration.this,DriverDetails.class);
            }
        });
    }
}