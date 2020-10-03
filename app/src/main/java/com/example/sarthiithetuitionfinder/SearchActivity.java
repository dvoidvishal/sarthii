package com.example.sarthiithetuitionfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {
    ListView searchListItems;
    EditText searchValue;
    String[] listItems = {"Android","IPhone","WindowsMobile","Blackberry", "WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchListItems = new ListView(this);
        searchValue = new EditText(this);
        searchValue.setText("Hi");
    }
}