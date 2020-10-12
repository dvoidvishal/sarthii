package com.example.sarthiithetuitionfinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarthiithetuitionfinder.Activity.AdvanceSearchActitvity;
import com.example.sarthiithetuitionfinder.Activity.LoginActivity;
import com.example.sarthiithetuitionfinder.Activity.UnderDevelopment;
import com.example.sarthiithetuitionfinder.Activity.UserProfile;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class DashBoard extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout searchView;
    TextView dashboardCenterCount;
    int centerCount = 0;
    FirebaseAuth mAuth;
    ImageView menuHam;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null) updateUi();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        PrefManager prefManager = new PrefManager(this);
        prefManager.setFirstTimeLaunch(true);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);

        searchView = new RelativeLayout(this);
        searchView = findViewById(R.id.search_view);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchPage = new Intent(DashBoard.this, Search.class);
                startActivity(searchPage);

            }
        });

        NavigationView navigationView = findViewById(R.id.navigation_view);
//        navigationView.setItemIconTintList(null);
//
//        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
//        NavigationUI.setupWithNavController(navigationView, navController);

        dashboardCenterCount = new TextView(this);
        dashboardCenterCount = findViewById(R.id.dashboardCenterCount);
//        DatabaseReference db = FirebaseDatabase.getInstance().getReference("tutions");
//        db.addValueEventListener(valueEventListener);
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("tutions").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                dashboardCenterCount.setText(String.valueOf(queryDocumentSnapshots.size()));
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.nav_logout) {
//                    Toast.makeText(DashBoard.this, "inside logout", Toast.LENGTH_SHORT).show();
                    doLogout();
                }else {
                    startActivityById(id);
//                    Toast.makeText(DashBoard.this, "inside else", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                for(DataSnapshot data: dataSnapshot.getChildren()) {
                    centerCount = centerCount + 1;
                    dashboardCenterCount.setText(String.valueOf(centerCount));
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public void updateUi() {
        Intent goToLoging = new Intent(this, LoginActivity.class);
        startActivity(goToLoging);
        finish();
    }

    public void doLogout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void openNav(View v) {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    public void openAdvanceSerach(View v) {
        Intent i = new Intent(this, AdvanceSearchActitvity.class);
        startActivity(i);
    }

    public void openUnderDevPage(View v) {
        startActivity(new Intent(this, UnderDevelopment.class));
    }

    public void startActivityById(int id) {
        switch (id) {
            case R.id.stud_profile:
                startActivity(new Intent(this, UserProfile.class));
                break;

            case R.id.join_tutions:
                startActivity(new Intent(this, Join_Tuitions.class ));

            case R.id.crash_course:
                startActivity(new Intent(this, Crash_Courses.class));

            case R.id.toprated_tutors:
                startActivity(new Intent(this, Best_Tutors.class));

            case R.id.parent_portal:
                startActivity(new Intent(this, Parent_Portal.class));

            case R.id.tutor_portal:
                startActivity(new Intent(this, Tutor_Portal.class));

            case R.id.notification:
                startActivity(new Intent(this, Notifications.class));

            case R.id.feedback:
                startActivity(new Intent(this, Feedback.class));

            case R.id.about:
                startActivity(new Intent(this, About_us.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

            default:
                Toast.makeText(this, "To Be Build By Vishal", Toast.LENGTH_SHORT).show();
        }
    }


}
