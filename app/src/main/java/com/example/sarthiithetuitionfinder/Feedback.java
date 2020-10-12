package com.example.sarthiithetuitionfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    RatingBar ratingBar;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ratingBar = findViewById(R.id.rating);
        editText = findViewById(R.id.comment);
        button = findViewById(R.id.feedback_submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = editText.getText().toString();
                float rat = ratingBar.getRating();

                String to[] = {"hi.simplicitydev@gmail.com"};
                String sub = "Feedback for app";

                if (comment.isEmpty() && rat == 0) {
                    Toast.makeText(Feedback.this, "Please rate the app!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent();
                    i.setData(Uri.parse("mailto:"));
                    i.putExtra(Intent.EXTRA_EMAIL, to);
                    i.putExtra(Intent.EXTRA_TEXT, "Rating:" + rat + "\n" + "Comment:" + comment);
                    i.putExtra(Intent.EXTRA_SUBJECT, sub);
                    startActivity(i.createChooser(i, "E-Mail"));
                }
            }
        });



    }
}
