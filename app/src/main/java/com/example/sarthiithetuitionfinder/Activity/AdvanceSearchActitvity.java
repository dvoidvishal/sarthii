package com.example.sarthiithetuitionfinder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarthiithetuitionfinder.R;
import com.example.sarthiithetuitionfinder.TutionModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AdvanceSearchActitvity extends AppCompatActivity {

    EditText centerName, location, subject;
    FirebaseDatabase db;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_search_actitvity);

        init();
        firebaseInit();
    }

    public void init() {
        centerName = new EditText(this);
        location = new EditText(this);
        subject = new EditText(this);

    }

    public void firebaseInit() {
        db = FirebaseDatabase.getInstance();
        mRef = db.getReference("tutions");
    }

    public void onAdvanceBtnClicked(View v) {
        Toast.makeText(this, "searching", Toast.LENGTH_SHORT).show();
        String name = centerName.getText().toString();
        String sub = subject.getText().toString();
        Query qurey = mRef.orderByChild("Subjects")
                .startAt(sub)
                .endAt(sub);
        qurey.addValueEventListener(valueEventListener);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Toast.makeText(AdvanceSearchActitvity.this, "data ", Toast.LENGTH_SHORT).show();
            if(dataSnapshot.exists()){
                for(DataSnapshot data: dataSnapshot.getChildren()) {
                    Log.d("=====", data.toString());
                }
            }else {
                Log.d("dataSnapshot", "no dataSnapshot");
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            System.out.println("databaseError :"  + databaseError);

        }
    };
}