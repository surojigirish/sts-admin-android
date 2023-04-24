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
import com.example.sts_admin.sharedpref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverRegistration extends AppCompatActivity {

    TextView text;
    EditText firstname,email, lastname,password,licenseNo, contactNo;
    Button regBtn;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registartion);

        initViews();

        sharedPrefManager = new SharedPrefManager(getApplicationContext());


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               register(registerRequest());
            }
        });
    }

    public void initViews() {
        text=findViewById(R.id.driverText);
        firstname =findViewById(R.id.driverName);
        email=findViewById(R.id.driverEmail);
        lastname =findViewById(R.id.driverUsername);
        password=findViewById(R.id.driverPassword);
        regBtn=findViewById(R.id.driverRegBtn);
        licenseNo = findViewById(R.id.et_driver_license);
        contactNo = findViewById(R.id.et_contact_number);

    }

    public RegisterRequest registerRequest(){
            RegisterRequest registerRequest=new RegisterRequest();
            registerRequest.setFirstname(firstname.getText().toString());
            registerRequest.setLastname(lastname.getText().toString());
            registerRequest.setEmail(email.getText().toString());
            registerRequest.setPassword(password.getText().toString());
            registerRequest.setLicenseNo(licenseNo.getText().toString());
            registerRequest.setContact(contactNo.getText().toString());
            registerRequest.setRole("driver");
            return registerRequest;
    }

    public void register(RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCall= ApiClient.getRoute().driverRegister(getUserSession(),registerRequest);

        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse=response.body();
                if (response.isSuccessful()){
                    if (registerResponse != null && registerResponse.getStatus() == 200) {
                        Toast.makeText(DriverRegistration.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DriverRegistration.this, AdminDashboard.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Toast.makeText(DriverRegistration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });
    }

    public String getUserSession() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        return sharedPrefManager.getUser().getToken();
    }
}