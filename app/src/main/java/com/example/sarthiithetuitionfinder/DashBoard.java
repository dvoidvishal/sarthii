package com.example.sarthiithetuitionfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class DashBoard extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        PrefManager prefManager = new PrefManager(this);
        prefManager.setFirstTimeLaunch(true);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        searchView = new RelativeLayout(this);
        searchView = findViewById(R.id.search_view);
        Intent searchPage = new Intent(DashBoard.this, Search.class);
        startActivity(searchPage);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchPage = new Intent(DashBoard.this, Search.class);
                startActivity(searchPage);
            }
        });
    }

}
