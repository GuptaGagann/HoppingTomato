package com.example.gagan.hoppingtomatoapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class dashboard_chef extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView mRecyclerView;
    private ArrayList<String> data = new ArrayList<String>();
    private RecyclerViewWithFooterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_chef);
        //initializeData();
        initializeRecyclerView();

    }

    private void initializeRecyclerView() {
        mRecyclerView = findViewById(R.id.addMenu);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewWithFooterAdapter(this, data);
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
