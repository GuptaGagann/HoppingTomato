package com.example.gagan.hoppingtomatoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText Email, Password;
    Button LogIn ;

    Button reg, pass;
    String name, password, email;

    ActionBarDrawerToggle toggle;
    String PasswordHolder, EmailHolder;
    String finalResult ;
    String HttpURL = "https://hoppingtomato.swatantranews.info/UserLogin.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    public static final String UserEmail = "";

    String firstname,lastname,registeredEmail,roleFlag,dob,gender,address,addressFlag;

    DrawerLayout mDrawerLayout;
    private static long back_pressed;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        if(sp.getBoolean("logged",false)){
            if(sp.getString("addressFlag","").equals("0")){
                Intent i =  new Intent();
                i = new Intent(this, MapsActivity.class);
                startActivity(i);
            }
            else {
                goToDashboard();
            }
        }
        mDrawerLayout = findViewById(R.id.drawer_layout);

        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        LogIn = (Button)findViewById(R.id.Login);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    UserLoginFunction(EmailHolder, PasswordHolder);

                }
                else {

                    Toast.makeText(MainActivity.this, "Please fill all the form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });
        reg = findViewById(R.id.button2);
        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity.this, signup.class);
                startActivity(i);
            }
        });

        pass = findViewById(R.id.button3);
        pass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity.this, forgot_pass.class);
                startActivity(i);
            }
        });

        toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void CheckEditTextIsEmptyOrNot(){

        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }
    public void UserLoginFunction(final String email, final String password){

        class UserLoginClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(MainActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if(httpResponseMsg.toString().equals("Email not registered or password incorrect! Try again.")
                        ||httpResponseMsg.toString().equals("Check Again.")){
                    Toast.makeText(MainActivity.this,httpResponseMsg,Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        getUserDetails(httpResponseMsg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email",params[0]);

                hashMap.put("password",params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(email,password);
    }

    private void getUserDetails(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        firstname = jsonArray.getJSONObject(0).getString("fname");
        lastname = jsonArray.getJSONObject(0).getString("lname");
        registeredEmail = jsonArray.getJSONObject(0).getString("uemail");
        roleFlag = jsonArray.getJSONObject(0).getString("role");
        dob = jsonArray.getJSONObject(0).getString("dob");
        gender = jsonArray.getJSONObject(0).getString("Gender");
        address = jsonArray.getJSONObject(0).getString("address");
        addressFlag = jsonArray.getJSONObject(0).getString("addRegistered");

        email = Email.getText().toString();
        if(registeredEmail.equals(email)){

            //Toast.makeText(MainActivity.this,addressFlag,Toast.LENGTH_LONG).show();
            sp.edit().putBoolean("logged",true).apply();
            sp.edit().putString("name", firstname+" "+lastname).apply();
            sp.edit().putString("email", email).apply();
            sp.edit().putString("roleFlag", roleFlag).apply();
            sp.edit().putString("dob", dob).apply();
            sp.edit().putString("gender", gender).apply();
            sp.edit().putString("address", address).apply();
            sp.edit().putString("addressFlag", addressFlag).apply();

            Intent intent = new Intent();
            finish();

            if(Integer.parseInt(addressFlag) != 1){
                intent = new Intent(MainActivity.this, MapsActivity.class);
            }

            else {
                if(Integer.parseInt(roleFlag)==1){
                    intent = new Intent(MainActivity.this, DashboardChef.class);
                }
                else if(Integer.parseInt(roleFlag)==0){
                    intent = new Intent(MainActivity.this, DashboardCustomer.class);
                }
            }
            startActivity(intent);

        }
        else{
            Toast.makeText(MainActivity.this,"Email not registered or password incorrect! Try again.",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
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
        getMenuInflater().inflate(R.menu.side, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.test_activity_ref) {
            Intent i = new Intent(MainActivity.this,DashboardChef.class);
            startActivity(i);
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

        } else if (id == R.id.nav_faq) {
            Intent i = new Intent(MainActivity.this, faq.class);
            startActivity(i);
        } else if (id == R.id.nav_about) {
            Intent i = new Intent(MainActivity.this, about.class);
            startActivity(i);
        } else if (id == R.id.nav_contact) {
            Intent i = new Intent(MainActivity.this, contact.class);
            startActivity(i);
        } else if (id == R.id.nav_social) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    public void goToDashboard(){
        Intent i =  new Intent();
        if(sp.getString("roleFlag","").equals("0")) {
            i = new Intent(this, DashboardCustomer.class);
        }
        else if(sp.getString("roleFlag","").equals("1")){
            i = new Intent(this, DashboardChef.class);
        }
        startActivity(i);
    }
}
