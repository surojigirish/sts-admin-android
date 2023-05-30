package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.AdminLogoutRequest;
import com.example.sts_admin.apiservice.response.AdminLogoutResponse;
import com.example.sts_admin.fragments.RouteSearchFragment;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverDashboard extends AppCompatActivity {

    CardView busScheduleList;

    Button driverLogoutBtn;

    private AppCompatButton btnValidatePassFrag;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dashboard);

        busScheduleList=findViewById(R.id.bus_schedule_list);
        driverLogoutBtn=findViewById(R.id.driverLogoutBtn);

        busScheduleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DriverDashboard.this,BusScheduleList.class);
                startActivity(intent);
            }
        });

        driverLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(logoutRequest());

            }
        });

        // button to validate pass fragment screen
        btnValidatePassFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteSearchFragment fragment = new RouteSearchFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout_validate_pass, fragment);
                transaction.commit();
            }
        });
    }

    public AdminLogoutRequest logoutRequest(){
        AdminLogoutRequest logoutRequest=new AdminLogoutRequest();
        logoutRequest.setToken(getSessionToken());
        return logoutRequest;
    }

    public void logout(AdminLogoutRequest logoutRequest){
        Call<AdminLogoutResponse> logoutResponseCall= Client.getInstance(Consts.BASE_URL_ADMIN).getRoute().logout(logoutRequest);
        logoutResponseCall.enqueue(new Callback<AdminLogoutResponse>() {
            @Override
            public void onResponse(Call<AdminLogoutResponse> call, Response<AdminLogoutResponse> response) {
                AdminLogoutResponse logoutResponse=response.body();
                if (response.isSuccessful()){
//                    Toast.makeText(AdminDashboard.this, "Logout successful", Toast.LENGTH_SHORT).show();
                    if(logoutResponse != null && logoutResponse.getStatus() == 200){
                        sharedPrefManager.logout();
                        Toast.makeText(DriverDashboard.this, "Logout successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(DriverDashboard.this, DriverLogin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    Toast.makeText(DriverDashboard.this, "onResponse: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AdminLogoutResponse> call, Throwable t) {
                Toast.makeText(DriverDashboard.this, "onFailure: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public String getSessionToken() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        return sharedPrefManager.getUser().getToken();
    }



    /*   Validate Pass    */

    // init views
    private void initializeViews() {
        btnValidatePassFrag = findViewById(R.id.appCompatButton_pass_validation);
    }

    //
}