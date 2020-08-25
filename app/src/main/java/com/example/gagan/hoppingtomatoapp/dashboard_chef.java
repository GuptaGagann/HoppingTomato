package com.example.gagan.hoppingtomatoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class dashboard_chef extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView mRecyclerView;
    private ArrayList<String> data = new ArrayList<String>();
    private AddMenuRecyclerViewWithFooterAdapter adapter;
    Button saveMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_chef);
        //initializeData();
        initializeRecyclerView();
        saveMenu = findViewById(R.id.saveMenu);
        saveMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard_chef.this, viewMenu.class));
            }
        });

    }

    private void initializeRecyclerView() {
        mRecyclerView = findViewById(R.id.addMenu);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddMenuRecyclerViewWithFooterAdapter(this, data);
        mRecyclerView.setAdapter(adapter);
    }

    private void initializeData() {
        for (int i = 0; i < 10; i++) data.add("Position :" + i);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
