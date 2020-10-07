package com.example.sarthiithetuitionfinder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sarthiithetuitionfinder.R;
import com.example.sarthiithetuitionfinder.TutionModal;

import java.util.HashMap;
import java.util.Map;

public class TutionProfile extends AppCompatActivity {
    TutionModal tutionData;
    String Name, Email, Subjects, Contact, NoOfBatches, Qualifications, Classes;
    TextView NameTv, EmailTv, SubjectsTv, ContactTv, NoOfBatchesTv, QualificationsTv, ClassesTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tution_profile);

        Bundle b = getIntent().getExtras();
        Name = b.getString("Name");
        Email = b.getString("Email");
        Subjects = b.getString("Subjects");
        Contact = b.getString("Contact");
        NoOfBatches = b.getString("NoOfBatches");
        Classes = b.getString("Classes");
        Qualifications = b.getString("Qualifications");


        NameTv = new EditText(this);
        NameTv = findViewById(R.id.tName);
        EmailTv = new EditText(this);
        EmailTv = findViewById(R.id.tEmail);
        SubjectsTv = new EditText(this);
        SubjectsTv = findViewById(R.id.tSubjects);
        ContactTv = new EditText(this);
        ContactTv = findViewById(R.id.tContact);
        NoOfBatchesTv = new EditText(this);
        NoOfBatchesTv = findViewById(R.id.tBatches);
        QualificationsTv = new EditText(this);
        QualificationsTv = findViewById(R.id.tQualifications);
        ClassesTv = new EditText(this);
        ClassesTv = findViewById(R.id.tClasses);

        updateData();
    }

    public void updateData() {
        NameTv.setText(Name);
        ContactTv.setText(Contact);
        EmailTv.setText(Email);
        NoOfBatchesTv.setText(NoOfBatches);
        QualificationsTv.setText(Qualifications);
        ClassesTv.setText(Classes);
        SubjectsTv.setText(Subjects);

    }


}