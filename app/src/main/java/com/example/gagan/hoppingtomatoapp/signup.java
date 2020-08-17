package com.example.gagan.hoppingtomatoapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class signup extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText t1, t2, t3, t4;
    Bundle b1=new Bundle();
    Button signup;
    Spinner dd,mm,yy;
    String date,year,month, name,email,password,cpassword;
    ArrayAdapter<String> a;
    String d[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String m[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String y[]={"1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010"};
    DrawerLayout sup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        t1=(EditText)findViewById(R.id.editText8);
        t1.requestFocus();
        t2=(EditText)findViewById(R.id.editText5);
        t3=(EditText)findViewById(R.id.editText6);
        t4=(EditText)findViewById(R.id.editText7);

        sup=(DrawerLayout)findViewById(R.id.drawer_layout);

        final Switch simpleSwitch = (Switch) findViewById(R.id.switch3);

        simpleSwitch.setChecked(false);
        sup.setBackground(getDrawable(R.drawable.dine1));
        sup.getBackground().setAlpha(120);

        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) //Line A
            {
                boolean isOn = ((Switch) simpleSwitch).isChecked();

                if (isOn)
                {
                    sup.setBackgroundResource(R.drawable.chef1);
                    sup.getBackground().setAlpha(130);
                    Toast.makeText(signup.this, "Signing up as Chef.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    sup.setBackground(getDrawable(R.drawable.dine1));
                    sup.getBackground().setAlpha(120);
                    Toast.makeText(signup.this, "Signing up as Customer.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signup=(Button)findViewById(R.id.button4);

        dd=(Spinner)findViewById(R.id.spinner);
        mm=(Spinner)findViewById(R.id.spinner1);
        yy=(Spinner)findViewById(R.id.spinner2);

        a=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, d);
        dd.setAdapter(a);
        dd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                date=arg0.getItemAtPosition(arg2).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        a=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
        mm.setAdapter(a);
        mm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                month=arg0.getItemAtPosition(arg2).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        a=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, y);
        yy.setAdapter(a);
        yy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                year=arg0.getItemAtPosition(arg2).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=t1.getText().toString();
                email=t2.getText().toString();
                password=t3.getText().toString();
                cpassword=t4.getText().toString();

                if(name.equals("")){
                    Toast.makeText(signup.this, "Name Can't be Empty.", 4000).show();
                }
                else if(email.equals("")){
                    Toast.makeText(signup.this, "Email Can't be Empty.", 4000).show();
                }
                else if(!VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches()){
                    Toast.makeText(signup.this, "Incorrect email.", 4000).show();
                }
                else if(password.equals("")){
                    Toast.makeText(signup.this, "Password Can't be Empty.", 4000).show();
                }
                else if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){
                    Toast.makeText(signup.this, "Password should be in appropriate format.", 4000).show();
                }
                else if(password.length()<8){
                    Toast.makeText(signup.this, "Password should be at least 8 characters long.", 4000).show();
                }
                else if(cpassword.equals("")){
                    Toast.makeText(signup.this, "Password Can't be Empty.", 4000).show();
                }
                else if(!password.equals(cpassword)){
                    Toast.makeText(signup.this, "Password doesn't match.", 4000).show();
                }
                else {
                    b1.putString("nm", name);
                    b1.putString("unm", email);
                    b1.putString("pwd", password);
                    Toast.makeText(signup.this, "Account created successfully!", 4000).show();
                    Intent i = new Intent(signup.this, MainActivity.class);
                    i.putExtras(b1);
                    startActivity(i);
                }
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
