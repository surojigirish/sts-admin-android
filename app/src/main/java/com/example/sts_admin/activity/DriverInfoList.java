package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.UpdateDriverRequest;
import com.example.sts_admin.apiservice.response.UpdateDriverDetailsResponse;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverInfoList extends AppCompatActivity {

    EditText driverFirstName, driverLastName, driverContactNo,driverEmployeeNo;
    TextView driverLicenseNo,driverGender;
    AppCompatButton updateDriver;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_info_list);

        driverFirstName=findViewById(R.id.driver_first_name);
        driverLastName=findViewById(R.id.driver_last_name);
        driverLicenseNo=findViewById(R.id.driver_license_no);
        driverContactNo=findViewById(R.id.driver_contact_no);
        driverGender=findViewById(R.id.driver_gender);
        updateDriver=findViewById(R.id.updateDriver);

        Intent i = getIntent();
        String firstname = i.getStringExtra("firstname");
        String lastname = i.getStringExtra("lastname");
        String licenseNo = i.getStringExtra("licenseNo");
        String contact = i.getStringExtra("contact");
        String gender = i.getStringExtra("gender");

        driverFirstName.setText(firstname);
        driverLastName.setText(lastname);
        driverLicenseNo.setText(licenseNo);
        driverContactNo.setText(contact);
        driverGender.setText(gender);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent i = getIntent();
        Integer id = i.getIntExtra("driverId",0);
        updateDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDriver(updateDriverRequest(),id);
            }
        });
    }

    public UpdateDriverRequest updateDriverRequest(){
        UpdateDriverRequest driverRequest  = new UpdateDriverRequest();
        driverRequest.setFirstname(driverFirstName.getText().toString());
        driverRequest.setLastname(driverLastName.getText().toString());
        driverRequest.setContact(driverContactNo.getText().toString());
        return driverRequest;
    }

    public void UpdateDriver(UpdateDriverRequest updateDriverRequest, Integer id){
        Call<UpdateDriverDetailsResponse> call = Client.getInstance(Consts.BASE_URL_ADMIN).getRoute().updateDriverDetails(id,updateDriverRequest,getUserSession());
        call.enqueue(new Callback<UpdateDriverDetailsResponse>() {
            @Override
            public void onResponse(Call<UpdateDriverDetailsResponse> call, Response<UpdateDriverDetailsResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(DriverInfoList.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), DriverDetails.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<UpdateDriverDetailsResponse> call, Throwable t) {

                Toast.makeText(DriverInfoList.this, "Update fail "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    // ================== user session token =================

    public String getUserSession() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        return sharedPrefManager.getUser().getToken();
    }
}