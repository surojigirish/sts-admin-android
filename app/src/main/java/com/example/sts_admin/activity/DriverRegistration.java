package com.example.sts_admin.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.DriverRegisterRequest;
import com.example.sts_admin.apiservice.response.DriverRegisterResponse;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverRegistration extends AppCompatActivity {

    TextView text;
    EditText firstname,email, lastname,password,licenseNo, contactNo, gender;
    AppCompatButton regBtn, driverDetailsBtn;

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

    @SuppressLint("WrongViewCast")
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
        gender=findViewById(R.id.et_gender);



    }

    public DriverRegisterRequest registerRequest(){
            DriverRegisterRequest registerRequest=new DriverRegisterRequest();
            registerRequest.setFirstname(firstname.getText().toString());
            registerRequest.setLastname(lastname.getText().toString());
            registerRequest.setEmail(email.getText().toString());
            registerRequest.setPassword(password.getText().toString());
            registerRequest.setLicenseNo(licenseNo.getText().toString());
            registerRequest.setContact(contactNo.getText().toString());
            registerRequest.setGender(gender.getText().toString());
            registerRequest.setRole("driver");

            return registerRequest;
    }

    private void validation() {

        if (firstname.getText().toString().isEmpty()) {
            firstname.setError("Field cannot be empty");
        } else if (firstname.getText().toString().length() <= 3) {
            firstname.setError(",ore than 3 char req");
        }

        if (lastname.getText().toString().isEmpty()) {
            lastname.setError("Field cannot be empty");
        } else if (lastname.getText().toString().length() <= 3) {
            lastname.setError(",ore than 3 char req");
        }

        if (email.getText().toString().isEmpty()) {
            email.requestFocus();
            email.setError("Field cannot be empty");
//        } else {
//            if (email.getText().toString().matches(!Pattern.matches(E,email))) {
//                email.requestFocus();
//                email.setText("Please Enter Correct Email");
//            }
        }
        if (password.getText().toString().isEmpty()){
            password.setError("password is must");
        } else if (password.getText().toString().length()<=5) {
            password.setError("should contain more than 5 char");

        }

        if (contactNo.getText().toString().length()>11){
            contactNo.setError("Enter 10 digit number");
        }


        if (licenseNo.getText().toString().isEmpty()) {
            // Show an error message that the license number is required
            Toast.makeText(getApplicationContext(), "License number is required.", Toast.LENGTH_SHORT).show();
        } else if (licenseNo.getText().toString().length() <= 6) {
            Toast.makeText(getApplicationContext(), "Invalid license number.", Toast.LENGTH_SHORT).show();
        }

    }
    public void register(DriverRegisterRequest registerRequest){


        Call<DriverRegisterResponse> registerResponseCall= Client.getInstance(Consts.BASE_URL_ADMIN).getRoute().driverRegister(getUserSession(),registerRequest);


        registerResponseCall.enqueue(new Callback<DriverRegisterResponse>() {
            @Override
            public void onResponse(Call<DriverRegisterResponse> call, Response<DriverRegisterResponse> response) {
                DriverRegisterResponse registerResponse=response.body();
                if (response.isSuccessful()){

                    if (registerResponse != null && registerResponse.getStatus() == 200) {
                        validation();
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