package com.example.sts_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sts_admin.sharedpref.SharedPrefManager;

public class Welcome extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    Button btn1;

    // SharedPrefManager init
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        imageView=findViewById(R.id.imageView2);
        textView=findViewById(R.id.textView);
        btn1=findViewById(R.id.button);

        initSharedPrefManager();
        findViewById(R.id.button).setOnClickListener(v->startActivity(new Intent(Welcome.this,AdminLogin.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (sharedPrefManager.isLogged()) {
            Intent intent = new Intent(Welcome.this, AdminDashboard.class);
            // setFlags
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }


    // initialize shared pref manager
    public void initSharedPrefManager() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
    }
}