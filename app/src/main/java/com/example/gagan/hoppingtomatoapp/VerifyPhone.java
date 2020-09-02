package com.example.gagan.hoppingtomatoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class VerifyPhone extends AppCompatActivity {

    private EditText otp;
    Button sendOTP, submitOTP;
    TextView phone_tv, updatePhone;
    String name,email,roleFlag,dob,gender,address,addressFlag,phone;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        phone_tv = findViewById(R.id.phone);
        otp = findViewById(R.id.otp);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        name = sp.getString("name","");
        email = sp.getString("email","");
        roleFlag = sp.getString("roleFlag","");
        dob = sp.getString("dob","");
        gender = sp.getString("gender","");
        address = sp.getString("address","");
        addressFlag = sp.getString("addressFlag","");
        phone = sp.getString("phone","");

        updatePhone = findViewById(R.id.updatePhone);
        sendOTP = findViewById(R.id.getOTP);
        submitOTP = findViewById(R.id.submitOTP);

        phone_tv.setText(phone);

        updatePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerifyPhone.this,MapsActivity.class));
            }
        });

        submitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(Integer.parseInt(roleFlag)==1){
                    intent = new Intent(VerifyPhone.this,DashboardChef.class);
                }

                else if(Integer.parseInt(roleFlag)==0){
                    intent = new Intent(VerifyPhone.this,DashboardCustomer.class);
                }
                startActivity(intent);
            }
        });

        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent i=new Intent(this, MapsActivity.class);
        startActivity(i);
    }
}
