package com.example.sts_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sts_admin.apiclient.ApiClient;
import com.example.sts_admin.driverRegistrationModel.RegisterRequest;
import com.example.sts_admin.driverRegistrationModel.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

               register(registerRequest());
            }
        });
    }

    public RegisterRequest registerRequest(){
            RegisterRequest registerRequest=new RegisterRequest();
            registerRequest.setFirstname(name.getText().toString());
            registerRequest.setLastname(username.getText().toString());
            registerRequest.setEmail(email.getText().toString());
            registerRequest.setPassword(password.getText().toString());
            return registerRequest;
    }

    public void register(RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCall= ApiClient.getRoute().driverRegister(registerRequest);

        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse=response.body();
                if (response.isSuccessful()){
                    Toast.makeText(DriverRegistration.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(DriverRegistration.this,AdminDashboard.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(DriverRegistration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });
    }

}