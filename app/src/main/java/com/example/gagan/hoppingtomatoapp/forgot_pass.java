package com.example.gagan.hoppingtomatoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class forgot_pass extends AppCompatActivity {

    Button submit;
    Bundle b1=new Bundle();
    String email;
    EditText t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        t1=(EditText)findViewById(R.id.editText3);
        email=t1.getText().toString();

        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        submit=(Button)findViewById(R.id.button5);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(!VALID_EMAIL_ADDRESS_REGEX.matcher(t1.getText().toString()).matches()){
                    Toast.makeText(forgot_pass.this, "Incorrect email.", Toast.LENGTH_SHORT).show();
                }
                else{
                    b1.putString("unm", email);
                    Toast.makeText(forgot_pass.this, "Password reset link sent on email.", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(forgot_pass.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent i=new Intent(forgot_pass.this, MainActivity.class);
        startActivity(i);
    }

}
