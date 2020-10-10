package com.example.sarthiithetuitionfinder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarthiithetuitionfinder.R;
import com.example.sarthiithetuitionfinder.Search;
import com.example.sarthiithetuitionfinder.SearchActivity;
import com.example.sarthiithetuitionfinder.TutionModal;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AdvanceSearchActitvity extends AppCompatActivity {

    EditText city, subject;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_search_actitvity);

        init();
        firebaseInit();
    }

    public void init() {
       city = new EditText(this);
       subject = new EditText(this);

       city = findViewById(R.id.advanceLocationCity);
       subject = findViewById(R.id.AdvanceSubject);

    }

    public void firebaseInit() {
        db = FirebaseFirestore.getInstance();
    }

    public void onAdvanceBtnClicked(View v) {
        Toast.makeText(this, "searching", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Search.class);
        i.putExtra("fromAdvance", 1);
        i.putExtra("city", city.getText().toString());
        i.putExtra("subject", subject.getText().toString());
        startActivity(i);
    }


}