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
import com.example.sts_admin.loginModel.LoginRequest;
import com.example.sts_admin.loginModel.LoginResponse;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        text = findViewById(R.id.adminText);
        email = findViewById(R.id.adminUsername);
        password = findViewById(R.id.adminPassword);
        loginBtn = findViewById(R.id.adminLoginBtn);

        tvIpAddress = findViewById(R.id.tv_ip);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(loginRequest());
            }
        });
    }
    
    
    // to create the login request
    public LoginRequest loginRequest(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        loginRequest.setIpaddress(getIpAddress());
        return loginRequest;
    }
    
    public void login(LoginRequest loginRequest) {
        Call<LoginResponse> loginResponseCall = ApiClient.getLoginAdminRoute().adminLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AdminLogin.this, "user successfully logged in", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AdminLogin.this,AdminDashboard.class);
                    startActivity(intent);
                    finish();
//                    tvIpAddress.setText(getIpAddress());
                } else {
                    Toast.makeText(AdminLogin.this, "login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(AdminLogin.this, "onFailure: " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

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