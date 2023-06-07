package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sts_admin.R;
import com.example.sts_admin.sharedpref.SharedPrefManager;

public class Welcome extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    AppCompatButton adminHomeLoginBtn,driverHomeLoginBtn;

    // SharedPrefManager init
    SharedPrefManager sharedPrefManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        imageView=findViewById(R.id.imageView4);
//        textView=findViewById(R.id.textView);
      adminHomeLoginBtn = findViewById(R.id.adminHomeLoginBtn);
      driverHomeLoginBtn = findViewById(R.id.driverHomeLoginBtn);

        initSharedPrefManager();
        findViewById(R.id.adminHomeLoginBtn).setOnClickListener(v->startActivity(new Intent(Welcome.this, AdminLogin.class)));
        findViewById(R.id.driverHomeLoginBtn).setOnClickListener(v->startActivity(new Intent(Welcome.this, DriverLogin.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (sharedPrefManager.isAdminLogged()) {
            Intent intent = new Intent(Welcome.this, AdminDashboard.class);
            // setFlags
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        // Check if driver user is logged in
        if (sharedPrefManager.isDriverLogged()) {
            Intent intent = new Intent(Welcome.this, DriverDashboard.class);
            // setFlags clears previous tasks
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    // initialize shared pref manager
    public void initSharedPrefManager() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
    }
}