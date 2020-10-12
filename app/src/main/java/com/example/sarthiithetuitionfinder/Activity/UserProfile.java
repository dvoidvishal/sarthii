package com.example.sarthiithetuitionfinder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.sarthiithetuitionfinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail();

        TextView text = new TextView(this);
        text = findViewById(R.id.textView3);
        text.setText("Hey, " + userEmail);

    }
}