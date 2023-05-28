package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sts_admin.R;

public class DriverLogin extends AppCompatActivity {


    TextView driverLoginEmail,driverLoginPassword;
    Button driverLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        driverLoginEmail=findViewById(R.id.driverLoginEmail);
        driverLoginPassword=findViewById(R.id.driverLoginPassword);
        driverLoginButton=findViewById(R.id.driverLoginBtn);

        driverLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DriverLogin.this,DriverDashboard.class);
                startActivity(intent);
            }
        });
    }
}