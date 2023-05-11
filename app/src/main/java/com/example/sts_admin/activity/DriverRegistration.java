package com.example.sts_admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.DriverRegisterRequest;
import com.example.sts_admin.apiservice.response.DriverRegisterResponse;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverRegistration extends AppCompatActivity {

    TextView text;
    EditText firstname,email, lastname,password,licenseNo, contactNo;
    Button regBtn, driverDetailsBtn;

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

        driverDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DriverRegistration.this, DriverDetails.class);
                startActivity(intent);
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
        driverDetailsBtn=findViewById(R.id.driverDetailsBtn);

    }

    public DriverRegisterRequest registerRequest(){
            DriverRegisterRequest registerRequest=new DriverRegisterRequest();
            registerRequest.setFirstname(firstname.getText().toString());
            registerRequest.setLastname(lastname.getText().toString());
            registerRequest.setEmail(email.getText().toString());
            registerRequest.setPassword(password.getText().toString());
            registerRequest.setLicenseNo(licenseNo.getText().toString());
            registerRequest.setContact(contactNo.getText().toString());
            registerRequest.setRole("driver");
            return registerRequest;
    }


    public void register(DriverRegisterRequest registerRequest){
        Call<DriverRegisterResponse> registerResponseCall= Client.getInstance(Consts.BASE_URL_ADMIN).getRoute().driverRegister(getUserSession(),registerRequest);

        registerResponseCall.enqueue(new Callback<DriverRegisterResponse>() {
            @Override
            public void onResponse(Call<DriverRegisterResponse> call, Response<DriverRegisterResponse> response) {
                DriverRegisterResponse registerResponse=response.body();
                if (response.isSuccessful()){
                    if (registerResponse != null && registerResponse.getStatus() == 200) {
                        Toast.makeText(DriverRegistration.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DriverRegistration.this, AdminDashboard.class);
                        startActivity(intent);
                        finish();
                    } else if (registerResponse != null && registerResponse.getStatus() == 400) {
                        Toast.makeText(DriverRegistration.this, "fields are mandatory", Toast.LENGTH_SHORT).show();
                    } else if (registerResponse != null && registerResponse.getStatus() == 405) {
                        Toast.makeText(DriverRegistration.this, "driver role required", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DriverRegistration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DriverRegisterResponse> call, Throwable t) {

            }
        });
    }

    public String getUserSession() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        return sharedPrefManager.getUser().getToken();
    }
}