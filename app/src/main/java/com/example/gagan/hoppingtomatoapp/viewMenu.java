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

public class viewMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView mRecyclerView;
    private ArrayList<String> data = new ArrayList<String>();
    private ViewMenuRecyclerView adapter;

    Button editMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu);
        //initializeData();
        initializeRecyclerView();
        editMenu = findViewById(R.id.editMenu);
        editMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewMenu.this, dashboard_chef.class));
            }
        });

    }

    private void initializeRecyclerView() {
        mRecyclerView = findViewById(R.id.menuChef);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewMenuRecyclerView(this, data);
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
