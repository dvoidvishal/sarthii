package com.example.sarthiithetuitionfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarthiithetuitionfinder.Adapter.TutionAdapter;
import com.example.sarthiithetuitionfinder.helpers.StringUtils;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity implements View.OnClickListener {
    EditText searchValue;
    Button searchBtn;

    RecyclerView searchListItems;

    SearchAdapter searchAdapter;
    List<SearchModal> searchModalList;

    TutionAdapter tutionAdapter;
    List<TutionModal> tutionModalList;


    FirebaseDatabase database;
    DatabaseReference dbCourses;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        searchValue = new EditText(this);
        searchValue = findViewById(R.id.searchValue);
        searchBtn = new Button(this);
        searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);

        searchListItems = new RecyclerView(this);
        searchListItems = findViewById(R.id.searchList);
        searchListItems.setLayoutManager(new LinearLayoutManager(this));
        searchListItems.hasFixedSize();

        searchModalList = new ArrayList<>();
        searchAdapter = new SearchAdapter(this, searchModalList);
        searchListItems.setAdapter(searchAdapter);

        tutionModalList = new ArrayList<>();
        tutionAdapter = new TutionAdapter(this, tutionModalList);
        searchListItems.setAdapter(tutionAdapter);

        database = FirebaseDatabase.getInstance();
        dbCourses = database.getReference("tutions");
//        dbCourses.addValueEventListener(valueEventListener);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null ) {
            int fromAdvance = bundle.getInt("fromAdvance", 0);
            if(fromAdvance != 0) {
                String city = bundle.getString("city");
                String subject = bundle.getString("subject");
                getAdvanceData(city.trim(), subject.trim());
            }else {
                getData(null);
            }

        }else {
            getData(null);
        }

    }

    public void getAdvanceData(String city, String subject){
        tutionModalList.clear();
        StringUtils utils = new StringUtils();
        db.collection("tutions")
                .whereEqualTo("City", city)
                .whereArrayContains("Subjects", utils.toTitleCase(subject))
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Log.d("document.getId()", document.getId() + " => " + document.getData());
                            tutionModalList.add(document.toObject(TutionModal.class));
                        }
                        tutionAdapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("onFailure: ", "onFailure: " + e);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchBtn:
                updateList();
                break;
        }
    }
    public void updateList() {
        String searchValueStr = searchValue.getText().toString();

        getData(searchValueStr);
    }

    public void getData(String value) {
        tutionModalList.clear();
        if(value != null) {
            db.collection("tutions")
                    .whereArrayContains("Subjects", new StringUtils().toTitleCase(value.trim())).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Log.d("document.getId()", document.getId() + " => " + document.getData());
                        tutionModalList.add(document.toObject(TutionModal.class));
                    }
                    tutionAdapter.notifyDataSetChanged();
                    Toast.makeText(Search.this, "Done", Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Search.this, "Error While Fetching Data", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            db.collection("tutions")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                Log.d("document.getId()", document.getId() + " => " + document.getData());
                                tutionModalList.add(document.toObject(TutionModal.class));
                            }
                            tutionAdapter.notifyDataSetChanged();
                            Toast.makeText(Search.this, "Done", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Search.this, "Error While Fetching Data", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void updateListFromRealDb() {
        String searchValueStr = searchValue.getText().toString();
        if(searchValueStr.length() > 0) {
            Query query = database.getReference("tutions")
                    .orderByChild("Name")
                    .startAt(searchValueStr);
            query.addValueEventListener(valueEventListener);
        }else {
            dbCourses = database.getReference("tutions");
            dbCourses.addValueEventListener(valueEventListener);
        }
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            tutionModalList.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot data: dataSnapshot.getChildren()) {
                    TutionModal tutionModal = data.getValue(TutionModal.class);
                     tutionModalList.add(tutionModal);
                }
                tutionAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}