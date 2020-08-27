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
    TextView regDetails, phone, updatePhone;
    Integer role_flag=1;

    String name,email,roleFlag,dob,gender,address,addressFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        phone = findViewById(R.id.phone);
        otp = findViewById(R.id.otp);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        roleFlag = getIntent().getStringExtra("roleFlag");
        dob = getIntent().getStringExtra("dob");
        gender = getIntent().getStringExtra("gender");
        address = getIntent().getStringExtra("address");
        addressFlag = getIntent().getStringExtra("addressFlag");

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
                Intent intent = new Intent();
                if(Integer.parseInt(roleFlag)==1){
                    intent = new Intent(VerifyPhone.this,DashboardChef.class);
                }

                else if(Integer.parseInt(roleFlag)==0){
                    intent = new Intent(VerifyPhone.this,DashboardCustomer.class);
                }

                intent.putExtra("name",name)
                        .putExtra("email",email)
                        .putExtra("roleFlag",roleFlag)
                        .putExtra("dob",dob)
                        .putExtra("gender",gender)
                        .putExtra("address",address)
                        .putExtra("addressFlag",addressFlag)
                        .putExtra("phone",phone.getText().toString());
                startActivity(intent);
            }
        });

        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
