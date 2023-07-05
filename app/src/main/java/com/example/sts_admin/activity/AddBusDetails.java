package com.example.sts_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.request.AddBusRequest;
import com.example.sts_admin.apiservice.response.AddBusResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBusDetails extends AppCompatActivity {

    EditText reg_no, capacity;
    AppCompatButton addBus,getBusDetails;
    Spinner busStatusSpinner,busTypeSpinner;
    String busStatusItem,busTypeItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus_details);

        reg_no = findViewById(R.id.bus_regno);
        capacity = findViewById(R.id.bus_capacity);
        busTypeSpinner = findViewById(R.id.bus_type_spinner);
        busStatusSpinner = findViewById(R.id.bus_status_spinner);
        addBus = findViewById(R.id.add_bus_details_btn); // btn
        getBusDetails = findViewById(R.id.get_bus_details_list);

        busType();
        busTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                setBusTypeItem(adapterView.getItemAtPosition(position).toString());
                Log.i("TAG", "onItemSelected: selected item "+adapterView.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle the case when no item is selected
            }
        });

        busStatus();
        busStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                setBusStatusItem(adapterView.getItemAtPosition(position).toString());
                Log.i("TAG", "onItemSelected: selected item "+adapterView.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle the case when no item is selected
            }
        });

        addBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reg_no.getText().toString().isEmpty()){
                    reg_no.setError("Required");
                } else if (capacity.getText().toString().isEmpty()) {
                    reg_no.setError(null);
                    capacity.setError("Required");
                }else {
                    capacity.setError(null);
                    AddBus(busRequest());
                }

            }
        });

        getBusDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddBusDetails.this,BusDetails.class);
                startActivity(i);
                finish();
            }
        });

    }

    // api call
    public AddBusRequest busRequest(){
        AddBusRequest busRequest = new AddBusRequest();
        busRequest.setRegNo(reg_no.getText().toString().toUpperCase());
        Log.i("TAG", "busRequest: regno : "+busRequest.getRegNo());
        busRequest.setCapacity(capacity.getText().toString());
        busRequest.setType(getBusTypeItem().toUpperCase());
        busRequest.setStatus(getBusStatusItem().toUpperCase());
        return busRequest;
    }

    public void AddBus(AddBusRequest busRequest) {
        Call<AddBusResponse> addBusResponseCall = Client.getInstance(Consts.BASE_URL_BUS).getRoute().addBus(busRequest);
        addBusResponseCall.enqueue(new Callback<AddBusResponse>() {
            @Override
            public void onResponse(Call<AddBusResponse> call, Response<AddBusResponse> response) {
                AddBusResponse addBusResponse = response.body();
                if (response.isSuccessful()) {
                    Log.d("TAG", "onResponse: BUS added successfully");
                    if (addBusResponse != null && addBusResponse.getStatus() == 200) {
                        Toast.makeText(AddBusDetails.this, "Bus Added successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddBusDetails.this, AdminDashboard.class);
                        // setFlags clears previous tasks
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                } else {

                    Toast.makeText(AddBusDetails.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddBusResponse> call, Throwable t) {
                Log.d("TAG", "onFailure : " + t.getLocalizedMessage());
                Toast.makeText(AddBusDetails.this, "onFailure: " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // bus status
    public void busStatus(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bus_status_spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        busStatusSpinner.setAdapter(adapter);
    }
    // bus type
    public void busType(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bus_type_spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        busTypeSpinner.setAdapter(adapter);
    }



// getter setter of bus status items
    public String getBusStatusItem() {
        return busStatusItem;
    }

    public void setBusStatusItem(String busStatusItem) {
        this.busStatusItem = busStatusItem;
    }

    public String getBusTypeItem() {
        return busTypeItem;
    }

    public void setBusTypeItem(String busTypeItem) {
        this.busTypeItem = busTypeItem;
    }
}
