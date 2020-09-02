package com.example.gagan.hoppingtomatoapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gagan.hoppingtomatoapp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;
    TextView hello;
    private EditText name_tv, phone, addLine1, addLine2, city_tv, state_tv, country_tv, pin_tv;
    private FloatingActionButton fabPickPlace;
    Button saveAddress;
    String HttpURL = "https://hoppingtomato.swatantranews.info/addSave.php";
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String NAME, PHONE,ADD1,ADD2,CITY,STATE,COUNTRY,PIN;
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    String finalResult ;
    String regDetails;

    String name,email,roleFlag,dob,gender,address,addressFlag;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        initViews();

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();


        fabPickPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(MapsActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        sp = getSharedPreferences("login",MODE_PRIVATE);
        name = sp.getString("name","");
        email = sp.getString("email","");
        roleFlag = sp.getString("roleFlag","");
        dob = sp.getString("dob","");
        gender = sp.getString("gender","");
        address = sp.getString("address","");
        addressFlag = sp.getString("addressFlag","");

        hello = findViewById(R.id.hello);
        hello.setText("Hello, "+name);

        saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();
                String address = NAME+","+PHONE+","+ADD1+","+ADD2+","+ CITY+","+ STATE+"," +COUNTRY+","+ COUNTRY;
                String addRegistered = "1";

                if(CheckEditText){
                    sp.edit().putString("address", address).apply();
                    sp.edit().putString("addressFlag", addRegistered).apply();
                    sp.edit().putString("phone", phone.getText().toString()).apply();
//                    Toast.makeText(getApplicationContext(),phone.getText().toString(),Toast.LENGTH_SHORT).show();

                    addressSave(address, email, addRegistered);
                }
                else {
                    Toast.makeText(MapsActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void CheckEditTextIsEmptyOrNot(){
        NAME = name_tv.getText().toString();
        PHONE=phone.getText().toString();
        ADD1=addLine1.getText().toString();
        ADD2=addLine2.getText().toString();
        CITY=city_tv.getText().toString();
        STATE=state_tv.getText().toString();
        COUNTRY=country_tv.getText().toString();
        PIN=pin_tv.getText().toString();


        if(TextUtils.isEmpty(NAME) || TextUtils.isEmpty(PHONE)|| TextUtils.isEmpty(ADD1)|| TextUtils.isEmpty(ADD2)|| TextUtils.isEmpty(CITY)|| TextUtils.isEmpty(STATE)|| TextUtils.isEmpty(COUNTRY)|| TextUtils.isEmpty(PIN))

        {
            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }
    }

    public void addressSave(final String ADDRESS, final String EMAIL, final String ADDREG ){

        class addressSaveClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(MapsActivity.this,"Loading Data","Loading...",true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                regDetails = httpResponseMsg;

                Intent intent = new Intent();
                if(Integer.parseInt(roleFlag)==1){
                    intent = new Intent(MapsActivity.this,DashboardChef.class);
                }

                else if(Integer.parseInt(roleFlag)==0){
                    intent = new Intent(MapsActivity.this,DashboardCustomer.class);
                }
                startActivity(intent);

                Toast.makeText(MapsActivity.this, regDetails, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("addr",params[0]);

                hashMap.put("emaill",params[1]);

                hashMap.put("addreg",params[2]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        addressSaveClass addressSaveClass = new addressSaveClass();

        addressSaveClass.execute(ADDRESS,EMAIL,ADDREG);
    }


    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fabPickPlace = findViewById(R.id.fab);
        name_tv = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        addLine1 = findViewById(R.id.addLine1);
        addLine2 = findViewById(R.id.addLine2);
        city_tv = findViewById(R.id.city);
        state_tv  = findViewById(R.id.state);
        country_tv = findViewById(R.id.country);
        pin_tv  = findViewById(R.id.pin);
        saveAddress = findViewById(R.id.saveAddress);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Snackbar.make(fabPickPlace, connectionResult.getErrorMessage() + "", Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stBuilder = new StringBuilder();
                String placename = String.format("%s", place.getName());
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                String address = String.format("%s", place.getAddress());
                stBuilder.append("Name: ");
                stBuilder.append(placename);
                stBuilder.append("\n");
                stBuilder.append("Latitude: ");
                stBuilder.append(latitude);
                stBuilder.append("\n");
                stBuilder.append("Logitude: ");
                stBuilder.append(longitude);
                stBuilder.append("\n");
                stBuilder.append("Address: ");
                stBuilder.append(address);

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(Double.parseDouble((latitude)), Double.parseDouble(longitude), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String add = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();

                    city_tv.setText(city);
                    state_tv.setText(state);
                    country_tv.setText(country);
                    pin_tv.setText(postalCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static long back_pressed;

    @Override
    public void onBackPressed() {

        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Back once again to exit the app!",
                    Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            sp.edit().putBoolean("logged",false).apply();
            Intent i = new Intent(MapsActivity.this,MainActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
