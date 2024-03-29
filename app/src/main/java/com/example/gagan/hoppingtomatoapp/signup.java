package com.example.gagan.hoppingtomatoapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

public class signup extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button register, log_in;
    EditText First_Name, Last_Name, Email, Password ;
    String F_Name_Holder, L_Name_Holder, EmailHolder, PasswordHolder, GENDER,DATE;
    String finalResult ;
    String HttpURL = "https://swatantranews.info/UserRegistration.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    TextView date;
    DrawerLayout sup;
    Integer role_flag=0;
    private String Role_Flag_Holder;
    private DatePickerDialog datePickerDialog;

    RadioGroup radioGroup;
    RadioButton selectedRadioButton;

    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        First_Name = findViewById(R.id.editTextF_Name);
        Last_Name = findViewById(R.id.editTextL_Name);
        Email = findViewById(R.id.editTextEmail);
        Password = findViewById(R.id.editTextPassword);

        sup= findViewById(R.id.drawer_layout);
        radioGroup= findViewById(R.id.radioGroup1);

        register = findViewById(R.id.Submit);
        log_in = findViewById(R.id.Login);

        final Switch simpleSwitch = findViewById(R.id.switch3);

        simpleSwitch.setChecked(false);
        sup.setBackground(getDrawable(R.drawable.dine1));
        sup.getBackground().setAlpha(120);
        role_flag=0;

        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) //Line A
            {
                boolean isOn = simpleSwitch.isChecked();

                if (isOn) {
                    role_flag=1;
                    sup.setBackgroundResource(R.drawable.chef1);
                    sup.getBackground().setAlpha(130);
                    Toast.makeText(signup.this, "Signing up as Chef.", Toast.LENGTH_SHORT).show();
                }
                else {
                    role_flag=0;
                    sup.setBackground(getDrawable(R.drawable.dine1));
                    sup.getBackground().setAlpha(120);
                    Toast.makeText(signup.this, "Signing up as Customer.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        date = findViewById(R.id.date);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(signup.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        //Adding Click Listener on button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the selected RadioButton of the group
                selectedRadioButton  = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){
                    // If EditText is not empty and CheckEditText = True then this block will execute.
                    //Toast.makeText(signup.this,role_flag.toString(),Toast.LENGTH_LONG).show();
                    UserRegisterFunction(F_Name_Holder,L_Name_Holder, EmailHolder, PasswordHolder, Role_Flag_Holder,GENDER,DATE);
                }
                else {
                    // If EditText is empty then this block will execute .
                    Toast.makeText(signup.this, "Please fill all form fields.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(signup.this,MainActivity.class);
                startActivity(intent);

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void CheckEditTextIsEmptyOrNot(){

        F_Name_Holder = First_Name.getText().toString();
        L_Name_Holder = Last_Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        Role_Flag_Holder = role_flag.toString();
        GENDER = selectedRadioButton.getText().toString();
        DATE=date.getText().toString();

        if(TextUtils.isEmpty(F_Name_Holder) ||
                TextUtils.isEmpty(L_Name_Holder) ||
                TextUtils.isEmpty(EmailHolder) ||
                TextUtils.isEmpty(PasswordHolder)) {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }

    }
    public void UserRegisterFunction(final String F_Name, final String L_Name, final String email, final String password, final String Role, final String Gender, final String Date){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(signup.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(signup.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("f_name",params[0]);

                hashMap.put("L_name",params[1]);

                hashMap.put("email",params[2]);

                hashMap.put("password",params[3]);

                hashMap.put("role",params[4]);

                hashMap.put("gender",params[5]);

                hashMap.put("date",params[6]);


                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(F_Name,L_Name,email,password,Role,Gender,Date);
    }



    //Additional
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (back_pressed + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(getBaseContext(), "Back once again to exit the app!",
                        Toast.LENGTH_SHORT).show();
                back_pressed = System.currentTimeMillis();
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(signup.this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_faq) {
            Intent i = new Intent(signup.this, faq.class);
            startActivity(i);
        } else if (id == R.id.nav_about) {
            Intent i = new Intent(signup.this, about.class);
            startActivity(i);
        } else if (id == R.id.nav_contact) {
            Intent i = new Intent(signup.this, contact.class);
            startActivity(i);
        } else if (id == R.id.nav_social) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
