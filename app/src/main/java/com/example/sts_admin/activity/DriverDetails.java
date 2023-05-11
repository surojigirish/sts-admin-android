package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.adapters.DriverAdapter;
<<<<<<< HEAD:app/src/main/java/com/example/sts_admin/DriverDetails.java
import com.example.sts_admin.apiservice.AuthClient;
import com.example.sts_admin.model.EmployeeDriverResponse;
import com.example.sts_admin.model.UserDriver;
=======
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.EmployeeDriverResponse;
import com.example.sts_admin.model.Driver;
>>>>>>> 7edc965b6fe8bd6df472f9f546f1113e51f3d1de:app/src/main/java/com/example/sts_admin/activity/DriverDetails.java
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    SharedPrefManager sharedPrefManager;

    List<Driver> userDriverList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);

        recyclerView = findViewById(R.id.rv_all_drivers_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getAllDrivers();

    }

    private void getAllDrivers() {

        Call<EmployeeDriverResponse> call = Client.getInstance(Consts.BASE_URL_ADMIN).getRoute().getDrivers(getUserSession());
        call.enqueue(new Callback<EmployeeDriverResponse>() {
            @Override
            public void onResponse(Call<EmployeeDriverResponse> call, Response<EmployeeDriverResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        userDriverList = response.body().getEmployee();
                        recyclerView.setAdapter(new DriverAdapter(getApplicationContext(), userDriverList));

                    }
                } else {
                    if (response.body() != null) {
                        Toast.makeText(DriverDetails.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<EmployeeDriverResponse> call, Throwable t) {
                Toast.makeText(DriverDetails.this, "onFailure: " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getUserSession() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        return sharedPrefManager.getUser().getToken();
    }

}