package com.example.gagan.hoppingtomatoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VerifyPhone extends AppCompatActivity {

    private EditText phone, otp;
    Button sendOTP, submitOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);
        phone = findViewById(R.id.phone);
        otp = findViewById(R.id.otp);
        sendOTP = findViewById(R.id.getOTP);
        submitOTP = findViewById(R.id.submitOTP);

        submitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerifyPhone.this,dashboard_chef.class));
            }
        });
    }
}
