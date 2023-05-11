package com.example.sts_admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.AdminLoginRequest;
import com.example.sts_admin.apiservice.response.AdminLoginResponse;
import com.example.sts_admin.sharedpref.SharedPrefManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLogin extends AppCompatActivity {

    TextView text;
    EditText email, password;
    Button loginBtn;
    TextView tvIpAddress;

    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        text = findViewById(R.id.adminText);
        email = findViewById(R.id.adminUsername);
        password = findViewById(R.id.adminPassword);
        loginBtn = findViewById(R.id.adminLoginBtn);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        tvIpAddress = findViewById(R.id.tv_ip);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(loginRequest());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // check if user is logged and start the dashboard intent
        if (sharedPrefManager.isLogged()) {
            Intent intent = new Intent(AdminLogin.this, AdminDashboard.class);
            // setFlags clears previous tasks
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    // to create the login request
    public AdminLoginRequest loginRequest(){
        AdminLoginRequest loginRequest = new AdminLoginRequest();
        loginRequest.setEmail(email.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        loginRequest.setIpaddress(getIpAddress());
        return loginRequest;
    }
    

    public void login(AdminLoginRequest loginRequest) {
        Call<AdminLoginResponse> loginResponseCall = Client.getInstance(Consts.BASE_URL_ADMIN).getRoute().adminLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<AdminLoginResponse>() {
            @Override
            public void onResponse(Call<AdminLoginResponse> call, Response<AdminLoginResponse> response) {
                AdminLoginResponse loginResponse = response.body();
                if (response.isSuccessful()) {
                    Log.d("LOGIN", "onResponse: successfully");
                    if (loginResponse != null && loginResponse.getStatus() == 200) {
                        sharedPrefManager.saveUser(loginResponse.getUser());
                        Toast.makeText(AdminLogin.this, "user successfully logged in", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminLogin.this, AdminDashboard.class);
                        // setFlags clears previous tasks
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        //                    tvIpAddress.setText(getIpAddress());
                    }
                } else {

                    Toast.makeText(AdminLogin.this, "login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AdminLoginResponse> call, Throwable t) {
                Log.d("TAG", "onFailure : " + t.getLocalizedMessage());
                Toast.makeText(AdminLogin.this, "onFailure: " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // extract ip address
    public String getIpAddress(){
        String ipAddress = "";
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
                            ipAddress = inetAddress.getHostAddress();
                            return ipAddress;
                        }
                    }
                }
            } catch (SocketException e) {
                throw new RuntimeException(e);
//                e.printStackTrace();
            }
        return ipAddress;
    }
    
}