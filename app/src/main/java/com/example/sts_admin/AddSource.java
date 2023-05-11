package com.example.sts_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sts_admin.addSourceModel.SourceRequest;
import com.example.sts_admin.addSourceModel.SourceResponse;
import com.example.sts_admin.apiservice.ScheduleClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSource extends AppCompatActivity {

    EditText sourceInput;
    Button addSourceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_source);

        sourceInput=findViewById(R.id.source);
        addSourceBtn=findViewById(R.id.addSourceBtn);

        addSourceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               source(sourceRequest());
            }
        });
    }

    public SourceRequest sourceRequest(){
       SourceRequest sourceRequest=new SourceRequest("name",1.02,2.03);
       sourceRequest.setName(sourceInput.getText().toString());
       return sourceRequest;
    }

    public void source(SourceRequest sourceRequest){

        Call<SourceResponse> sourceResponseCall= ScheduleClient.getRoute().addSource(sourceRequest);
        sourceResponseCall.enqueue(new Callback<SourceResponse>() {
            @Override
            public void onResponse(Call<SourceResponse> call, Response<SourceResponse> response) {
                SourceResponse sourceResponse=response.body();
                if (response.isSuccessful()){
                    if (sourceResponse != null && sourceResponse.getStatus() == 200) {
                        Intent intent=new Intent(AddSource.this,MapsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<SourceResponse> call, Throwable t) {

            }
        });

    }
}