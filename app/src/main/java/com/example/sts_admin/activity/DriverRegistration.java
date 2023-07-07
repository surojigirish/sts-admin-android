package com.example.sts_admin.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.DriverRegisterRequest;
import com.example.sts_admin.apiservice.response.DriverRegisterResponse;
import com.example.sts_admin.model.Driver;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverRegistration extends AppCompatActivity {

    TextView text;
    EditText firstname,email, lastname,password,licenseNo, contactNo, gender;
    AppCompatButton regBtn, driverDetailsBtn;
    AppCompatImageButton backButton;

    SharedPrefManager sharedPrefManager;
    Spinner spinner_gender;
    String driverRegGenderData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registartion);

        initViews();


        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        getGenderData();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Fname = firstname.getText().toString().trim();
                String Lname = lastname.getText().toString().trim();
                String Lno = licenseNo.getText().toString().trim();
                String Contact = contactNo.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Pass = password.getText().toString().trim();

                if (Fname.isEmpty()) { //-----------------------------------firstname
                    firstname.setError("First name Required");
                } else if (Fname.length() < 3) {
                    firstname.setError("Minimum 3 characters required");
                } else if (!isValidString(Fname)) {
                    firstname.setError("Invalid input");
                }else if (Lname.isEmpty()) { //-----------------------------lastname
                    lastname.setError("Last name Required");
                } else if (Lname.length() < 3) {
                    lastname.setError("Minimum 3 characters required");
                } else if (!isValidString(Fname)) {
                    lastname.setError("Invalid input");
                }else if (Lno.isEmpty()) { //-----------------------------license
                    licenseNo.setError("Enter License No.");
                } else if (Lno.length() < 6) {
                    licenseNo.setError("Minimum 6 characters required");
                }else if (Contact.isEmpty()) { //--------------------------- contact
                    contactNo.setError("Enter Contact");
                } else if (Contact.length()!=10) {
                    contactNo.setError("10 characters required");
                }else if (spinner_gender.getSelectedItemPosition() == 0) { // -------------gender
                    TextView errorText = (TextView) spinner_gender.getSelectedView();
                    errorText.setError("SELECT GENDER");
                    errorText.setTextColor(Color.RED);
                    errorText.setText("Please select gender");
                }else if (Email.isEmpty()) { //-------------------------------email
                    email.setError("Enter Email-id");
                } else if (!isValidEmail(Email)) {
                    email.setError("Invalid email address");
                }else  if (Pass.isEmpty()) { //-----------------------------------password
                    password.setError("Enter password");
                } else if (Pass.length() < 3) {
                    password.setError("Minimum 3 characters required");
                } else {
                    register(registerRequest());
                }


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
        spinner_gender=findViewById(R.id.et_gender);
        backButton=findViewById(R.id.back_btn_add_driver_reg_screen);

    }

    public DriverRegisterRequest registerRequest(){
            DriverRegisterRequest registerRequest=new DriverRegisterRequest();
            registerRequest.setFirstname(firstname.getText().toString().toUpperCase());
            registerRequest.setLastname(lastname.getText().toString().toUpperCase());
            registerRequest.setEmail(email.getText().toString());
            registerRequest.setPassword(password.getText().toString());
            registerRequest.setLicenseNo(licenseNo.getText().toString().toUpperCase());
            registerRequest.setContact(contactNo.getText().toString());
            registerRequest.setGender(getDriverRegGenderData());
        Log.i("TAG", "registerRequest: gender "+getDriverRegGenderData());
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

    // gender data
    public void getGenderData(){
        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.Gender, android.R.layout.simple_spinner_item);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(adapterGender);

        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                setDriverRegGenderData(adapterView.getItemAtPosition(position).toString());
                Log.i("TAG", "onItemSelected: selected gender : "+getDriverRegGenderData());

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle the case when no item is selected
            }
        });
    }


    // Function to check if the string contains only alphabetic characters
    private boolean isValidString(String input) {
        String regex = "^[a-zA-Z]+$";
        return input.matches(regex);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }



    /*----------------------- getter and setter to store gender spinner data*/
    public String getDriverRegGenderData() {
        return driverRegGenderData;
    }
    public void setDriverRegGenderData(String driverRegGenderData) {
        this.driverRegGenderData = driverRegGenderData;
    }
}