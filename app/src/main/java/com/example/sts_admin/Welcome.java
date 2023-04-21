package com.example.sts_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        imageView=findViewById(R.id.imageView2);
        textView=findViewById(R.id.textView);
        btn1=findViewById(R.id.button);



        findViewById(R.id.button).setOnClickListener(v->startActivity(new Intent(Welcome.this,AdminLogin.class)));

    }
}