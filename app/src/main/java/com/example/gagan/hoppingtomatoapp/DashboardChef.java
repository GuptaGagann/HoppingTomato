package com.example.gagan.hoppingtomatoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DashboardChef extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle toggle;
    private RecyclerView mRecyclerView;
    private ArrayList<String> data = new ArrayList<String>();
    private AddMenuRecyclerViewWithFooterAdapter adapter;
    Button saveMenu;

    TabLayout tabLayout;
    ViewPager viewPager;

    TextView helloChef;

    String name,email,roleFlag,dob,gender,address,addressFlag,phone;

    private static long back_pressed;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_chef);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        name = sp.getString("name","");
        email = sp.getString("email","");
        roleFlag = sp.getString("roleFlag","");
        dob = sp.getString("dob","");
        gender = sp.getString("gender","");
        address = sp.getString("address","");
        addressFlag = sp.getString("addressFlag","");
        phone = sp.getString("phone","");

        helloChef = findViewById(R.id.helloChef);
        helloChef.setText("Hello Chef, "+name);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setCurrentItem(0);
        tabLayout.addTab(tabLayout.newTab().setText("Add/Update Menu"));
        tabLayout.addTab(tabLayout.newTab().setText("Manage Menu"));
        tabLayout.addTab(tabLayout.newTab().setText("Orders"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_faq) {
            Intent i = new Intent(DashboardChef.this, faq.class).putExtra("roleFlag",roleFlag);
            startActivity(i);
        } else if (id == R.id.nav_about) {
            Intent i = new Intent(DashboardChef.this, about.class).putExtra("roleFlag",roleFlag);
            startActivity(i);
        } else if (id == R.id.nav_contact) {
            Intent i = new Intent(DashboardChef.this, contact.class).putExtra("roleFlag",roleFlag);
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

    @Override
    public void onBackPressed(){
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {

            if (back_pressed + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(getBaseContext(), "Back once again to exit the app!",
                        Toast.LENGTH_SHORT).show();
                back_pressed = System.currentTimeMillis();
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            moveTaskToBack(true);
            }
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
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
