package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.DriverLoginRequest;
import com.example.sts_admin.apiservice.response.DriverLoginResponse;
import com.example.sts_admin.apiservice.response.DriverRegisterResponse;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverLogin extends AppCompatActivity {


    TextView driverLoginEmail, driverLoginPassword,driverIpAddress;
    AppCompatButton driverLoginButton;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        driverLoginEmail = findViewById(R.id.driverLoginEmail);
        driverLoginPassword = findViewById(R.id.driverLoginPassword2);
        driverLoginButton = findViewById(R.id.driverLoginBtn);

        driverIpAddress=findViewById(R.id.driverIpAddress);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        driverLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              driverLogin(driverLoginRequest());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public DriverLoginRequest driverLoginRequest() {
        DriverLoginRequest driverLoginRequest = new DriverLoginRequest();
        driverLoginRequest.setEmail(driverLoginEmail.getText().toString());
        driverLoginRequest.setPassword(driverLoginPassword.getText().toString());
        driverLoginRequest.setIpaddress(getDriverIpAddress());
        return driverLoginRequest;
    }


    public void driverLogin(DriverLoginRequest driverLoginRequest) {
        Call<DriverLoginResponse> driverLoginResponseCall= Client.getInstance(Consts.BASE_URL_ADMIN).getRoute().driverLogin(driverLoginRequest);

        driverLoginResponseCall.enqueue(new Callback<DriverLoginResponse>() {
            @Override
            public void onResponse(Call<DriverLoginResponse> call, Response<DriverLoginResponse> response) {
                DriverLoginResponse driverLoginResponse = response.body();
                if (response.isSuccessful()) {
                    if (driverLoginResponse != null && driverLoginResponse.getStatus() == 200) {
                        sharedPrefManager.saveDriver(driverLoginResponse.getSession());
                        Toast.makeText(DriverLogin.this, "Driver Successfully Logged In", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DriverLogin.this, DriverDashboard.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<DriverLoginResponse> call, Throwable t) {

            }
        });
    }

        // extract ip address
        public String getDriverIpAddress () {
            String driverIpAddress = "";
            try {
                // get list of all network interfaces
                Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (enumNetworkInterfaces.hasMoreElements()) {
                    NetworkInterface networkInterface = enumNetworkInterfaces.nextElement();
                    // get list of all ip addresses assigned
                    Enumeration<InetAddress> enumInetAddress = networkInterface.getInetAddresses();
                    while (enumInetAddress.hasMoreElements()) {
                        InetAddress inetAddress = enumInetAddress.nextElement();
                        if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                            // return the ip address as a string
                            driverIpAddress = inetAddress.getHostAddress();
                            return driverIpAddress;
                        }
                    }
                }
            } catch (SocketException e) {
                throw new RuntimeException(e);
//                e.printStackTrace();
            }
            return driverIpAddress;
        }



    }