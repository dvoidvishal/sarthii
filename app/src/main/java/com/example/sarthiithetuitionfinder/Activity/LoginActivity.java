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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText email, pass;
    Button login, signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = new EditText(this);
        pass = new EditText(this);
        login = new Button(this);
        signup = new Button(this);

        email = findViewById(R.id.authLoginEmail);
        pass = findViewById(R.id.authLoginPass);

        login = findViewById(R.id.authLoginLBtn);
        signup = findViewById(R.id.authLoginRBtn);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.authLoginLBtn:
                doLogin();
                break;
            case R.id.authLoginRBtn:
                startActivity(new Intent(this, SignupActivity.class));
                break;
        }
    }
    public void doLogin() {
        login.setEnabled(false);
        String userEmail = email.getText().toString(), userPass = pass.getText().toString();
        if(userEmail.equals("") || userPass.equals("")) {
            Toast.makeText(this, "Invalid Email/Password", Toast.LENGTH_SHORT);
            login.setEnabled(true);
        }else {
            Toast.makeText(this, "Please wait while we log you in!", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail, userPass)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            login.setEnabled(true);
                            Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, DashBoard.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    login.setEnabled(true);
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}