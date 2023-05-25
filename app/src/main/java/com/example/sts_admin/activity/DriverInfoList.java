package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sts_admin.R;

public class DriverInfoList extends AppCompatActivity {

    TextView driverFirstName, driverLastName, driverLicenseNo,driverContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_info_list);

        driverFirstName=findViewById(R.id.driver_first_name);
        driverLastName=findViewById(R.id.driver_last_name);
        driverLicenseNo=findViewById(R.id.driver_license_no);
        driverContactNo=findViewById(R.id.driver_contact_no);

        Intent i = getIntent();
        String firstname = i.getStringExtra("firstname");
        String lastname = i.getStringExtra("lastname");
        String licenseNo = i.getStringExtra("licenseNo");
        String contact = i.getStringExtra("contact");
        driverFirstName.setText(firstname);
        driverLastName.setText(lastname);
        driverLicenseNo.setText(licenseNo);
        driverContactNo.setText(contact);
    }
}