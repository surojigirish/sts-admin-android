package com.example.sts_admin.activity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.sts_admin.Consts;
import com.example.sts_admin.R;
import com.example.sts_admin.apiservice.Client;
import com.example.sts_admin.apiservice.response.PassengerProfileImageResponse;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPassengerDetails extends AppCompatActivity {

    TextView tvname,tvEmail,tvContact, tvAddress, tvGender, tvCategory;
    CircleImageView profile_picture;
    AppCompatImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_passenger_details);

        initViews();

        Intent i = getIntent();

        tvname.setText(i.getStringExtra("pName"));
        tvAddress.setText(i.getStringExtra("pAddress"));
        tvGender.setText(i.getStringExtra("pGender"));
        tvCategory.setText(i.getStringExtra("pCategory"));
        tvContact.setText(i.getStringExtra("pContact"));
        tvEmail.setText(i.getStringExtra("pEmail"));

//                String imagePath = i.getStringExtra("pPhoto");
//                profile_picture.setImageURI(Uri.parse(Consts.BASE_URL_USER+"/"+Consts.BASE_URL_GET_PASSENGER_PROFILE+""+imagePath));

    }

    @Override
    protected void onStart() {
        super.onStart();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GetPassengerList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        tvname = findViewById(R.id.name_text);
        tvEmail = findViewById(R.id.email_text);
        tvContact = findViewById(R.id.contact_text);
        tvAddress = findViewById(R.id.address_text);
        tvGender = findViewById(R.id.genger_text);
        tvCategory = findViewById(R.id.category_text);
        profile_picture = findViewById(R.id.passenger_profile_picture);
        backButton = findViewById(R.id.back_btn_passenger_details_screen);

    }
}