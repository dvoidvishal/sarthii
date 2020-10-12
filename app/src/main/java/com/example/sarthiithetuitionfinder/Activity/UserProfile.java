package com.example.sarthiithetuitionfinder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarthiithetuitionfinder.R;
import com.example.sarthiithetuitionfinder.TutionModal;
import com.example.sarthiithetuitionfinder.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class UserProfile extends AppCompatActivity {

    String userUid, userEmail;
    Map<String, String> userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_profile);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail = user.getEmail();
        userUid = user.getUid();

        userData = new HashMap<>();

        getUserData();

    }

    public void getUserData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("userData");
        Log.d("getUserData", "getUserData: " + userEmail);
        Query query = ref
                .orderByChild("email")
                .equalTo(userEmail);
        query.addValueEventListener(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                for(DataSnapshot data: dataSnapshot.getChildren()) {
//                    Users user = data.getValue(Users.class);
                    Log.d("onDataChange", "onDataChange: " + data);
                    try{
                        userData = (HashMap) data.getValue();
                        TextView name = new TextView(UserProfile.this);
                        TextView email = new TextView(UserProfile.this);

                        name = findViewById(R.id.username);
                        email = findViewById(R.id.emailtv);
                        name.setText(userData.get("name"));
                        email.setText(userData.get("email"));

                    }catch (Exception r) {
                        Log.d("onDataChange", "onDataChange: " + r);
                    }
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(UserProfile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("onCancelled: ", "onCancelled: "+ error.getMessage());
        }
    };
}