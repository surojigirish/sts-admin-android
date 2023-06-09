package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.AddBusRequest;
import com.example.sts_admin.apiservice.request.UpdateBusDetailsRequest;
import com.example.sts_admin.apiservice.response.UpdateBusDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusDetailsList extends AppCompatActivity {

    TextView busDetailsId, busDetailsCapacity;

    EditText busDetailsType,busDetailsStatus;

    Button updateStatusType;
//    Spinner busTypeSpinner;
//
//    String busTypeItem;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details_list);
//
        busDetailsId = findViewById(R.id.tv_bus_id);
        busDetailsCapacity= findViewById(R.id.tv_bus_capacity);
        busDetailsStatus = findViewById(R.id.et_bus_status);
        busDetailsType= findViewById(R.id.et_bus_details_type);
//        busTypeSpinner = findViewById(R.id.bus_type_details_spinner);
        updateStatusType=findViewById(R.id.updateStatusType);

        Intent i = getIntent();
        Integer busId = i.getIntExtra("busId",0);
        Integer busCapacity = i.getIntExtra("busCapacity",0);
        String busStatus = i.getStringExtra("busStatus");
        String busType = i.getStringExtra("busType");



        busDetailsCapacity.setText(busCapacity.toString());
        busDetailsId.setText(busId.toString());
        busDetailsStatus.setText(busStatus);
        busDetailsType.setText(busType);

//        busType();

//        busTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//                setBusTypeItem(adapterView.getItemAtPosition(position).toString());
//                Log.i("TAG", "onItemSelected: selected item "+adapterView.getItemAtPosition(position).toString());
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                // Handle the case when no item is selected
//            }
//        });


        updateStatusType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBusDetails(busId, updateBusDetail());

            }
        });



    }




    public UpdateBusDetailsRequest updateBusDetail(){

        UpdateBusDetailsRequest updateBusDetailsRequest = new UpdateBusDetailsRequest();

        String updateStatus =  busDetailsStatus.getText().toString();
        String updateType = busDetailsType.getText().toString();


        updateBusDetailsRequest.setStatus(updateStatus);
        updateBusDetailsRequest.setType(updateType);


        return updateBusDetailsRequest;
    }


    //
    public void updateBusDetails(int busId, UpdateBusDetailsRequest updateBusDetailsRequest){

        Call<UpdateBusDetailsResponse> updateBusDetailsResponseCall = Client.getInstance(Consts.BASE_URL_BUS).getRoute().updateBusDetails(busId, updateBusDetailsRequest);

        updateBusDetailsResponseCall.enqueue(new Callback<UpdateBusDetailsResponse>() {
            @Override
            public void onResponse(Call<UpdateBusDetailsResponse> call, Response<UpdateBusDetailsResponse> response) {
                if (response.isSuccessful()){
                    Log.i("TAG", "onResponse: Bus Update Successfully");
                    Toast.makeText(BusDetailsList.this, "Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BusDetailsList.this,BusDetails.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UpdateBusDetailsResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: unsuccessful");
            }
        });
    }

//    // adapter for bus type spinner
//    // bus type
//    public void busType(){
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.bus_type_spinner_items, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        busTypeSpinner.setAdapter(adapter);
//    }
//
//    public String getBusTypeItem() {
//        return busTypeItem;
//    }
//
//    public void setBusTypeItem(String busTypeItem) {
//        this.busTypeItem = busTypeItem;
//    }
}