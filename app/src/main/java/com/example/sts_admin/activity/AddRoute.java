package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.AddRequest;
import com.example.sts_admin.apiservice.response.AddResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRoute extends AppCompatActivity {

    EditText source,destination;
    Button addRoute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        source=findViewById(R.id.source);
        destination=findViewById(R.id.destination);
        addRoute=findViewById(R.id.button2);

        addRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                route(addRequest());
            }
        });


    }


    public AddRequest addRequest() {
        AddRequest addRequest=new AddRequest();

        addRequest.setSource(source.getText().toString());
        addRequest.setDestination(destination.getText().toString());
        return addRequest;

    }

   public void route(AddRequest addRequest){
        Call<AddResponse> addResponseCall= Client.getInstance(Consts.BASE_URL_SCHEDULE).getRoute().addRoute(addRequest);
        addResponseCall.enqueue(new Callback<AddResponse>() {
            @Override
            public void onResponse(Call<AddResponse> call, Response<AddResponse> response) {
                AddResponse addResponse = response.body();
                if (response.isSuccessful()) {
                    if (addResponse != null && addResponse.getStatus() == 200) {
                        Toast.makeText(AddRoute.this, "Route Added  Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddRoute.this, UpdateSchedule.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(AddRoute.this, "Failed To Add", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<AddResponse> call, Throwable t) {

            }
        });
    }
}