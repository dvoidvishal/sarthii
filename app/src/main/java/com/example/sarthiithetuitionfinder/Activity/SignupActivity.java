package com.example.sarthiithetuitionfinder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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
import java.util.List;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, LocationListener {

    EditText name, email, password, adress;
    Button sigup, login;
    ImageView btn;
    RadioButton r1,r2;
    LocationManager lm;
    LocationListener locationListener;
    String address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);


        lm= (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        name = new EditText(this);
        email = new EditText(this);
        password = new EditText(this);

        name = findViewById(R.id.authSignupName);
        email = findViewById(R.id.authSignupEmail);
        password = findViewById(R.id.authSignupPass);

        sigup = new Button(this);
        login = new Button(this);
        btn= (ImageView) findViewById(R.id.addresslocation);
        adress= (EditText) findViewById(R.id.addressbox);
        r1= (RadioButton) findViewById(R.id.radioButton1);
        r2= (RadioButton) findViewById(R.id.radioButton2);

        sigup = findViewById(R.id.authSignupBtn);
        login = findViewById(R.id.authSignupLoginBtn);
        sigup.setOnClickListener(this);
        login.setOnClickListener(this);

        //code to get location of user

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(SignupActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SignupActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SignupActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},0);
                    return;
                }
                btn.setEnabled(false);
                Toast.makeText(SignupActivity.this, "Please wait while we fetch your location", Toast.LENGTH_LONG).show();
                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, SignupActivity.this);
            }
        });

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
        if(userEmail.equals("") || userPass.equals("")) {
            sigup.setEnabled(true);
            Toast.makeText(this, "Please fill all the Details", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Please wait while we create your account", Toast.LENGTH_SHORT).show();
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
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                sigup.setEnabled(true);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("onLocationChanged: ", "onLocationChanged: "+ location);
        Geocoder g = new Geocoder(this);
        try{
            List<Address> add = g.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            Log.d("onLocationChanged: ", "onLocationChanged: " + add);
            adress.setText(add.get(0).getAddressLine(0));
            btn.setEnabled(true);

        }catch (Exception e) {
            adress.setText(e.getMessage());
            btn.setEnabled(true);
        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
        btn.setEnabled(true);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
        btn.setEnabled(true);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
        btn.setEnabled(true);
    }
}