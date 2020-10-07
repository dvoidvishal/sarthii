package com.example.sarthiithetuitionfinder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarthiithetuitionfinder.DashBoard;
import com.example.sarthiithetuitionfinder.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, email, password;
    Button sigup, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = new EditText(this);
        email = new EditText(this);
        password = new EditText(this);

        name = findViewById(R.id.authSignupName);
        email = findViewById(R.id.authSignupEmail);
        password = findViewById(R.id.authSignupPass);

        sigup = new Button(this);
        login = new Button(this);

        sigup = findViewById(R.id.authSignupBtn);
        login = findViewById(R.id.authSignupLoginBtn);
        sigup.setOnClickListener(this);
        login.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.authSignupLoginBtn:
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                break;
            case R.id.authSignupBtn:
                doSignup();
                break;
        }
    }

    public void doSignup() {
        sigup.setEnabled(false);
        String userEmail = email.getText().toString(), userPass = password.getText().toString();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(userEmail, userPass)
        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                saveUserData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                sigup.setEnabled(true);
            }
        });
    }
    public void saveUserData() {
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("userData");
        String uid = mRef.push().getKey();
        Map<String, String> userData = new HashMap<>();
        userData.put("name", name.getText().toString());
        userData.put("email", email.getText().toString());
        userData.put("uid", uid);
        mRef.child(uid).setValue(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                sigup.setEnabled(true);
                startActivity(new Intent(SignupActivity.this, DashBoard.class));
                finish();
                Toast.makeText(SignupActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}