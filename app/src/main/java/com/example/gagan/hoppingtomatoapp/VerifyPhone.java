package com.example.gagan.hoppingtomatoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VerifyPhone extends AppCompatActivity {

    private EditText otp;
    Button sendOTP, submitOTP;
    TextView phone, updatePhone;
    Integer role_flag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);
        phone = findViewById(R.id.phone);
        otp = findViewById(R.id.otp);

        updatePhone = findViewById(R.id.updatePhone);
        sendOTP = findViewById(R.id.getOTP);
        submitOTP = findViewById(R.id.submitOTP);

        phone.setText(getIntent().getStringExtra("phone").toString());

        updatePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerifyPhone.this,MapsActivity.class));
            }
        });

        submitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(role_flag==1){
                    startActivity(new Intent(VerifyPhone.this,DashboardChef.class));
                }

                else if(role_flag==0){
                    startActivity(new Intent(VerifyPhone.this,DashboardCustomer.class));
                }

                else{
                    Toast.makeText(v.getContext(),"No role found!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerifyPhone.this,DashboardCustomer.class));
            }
        });
    }
}
