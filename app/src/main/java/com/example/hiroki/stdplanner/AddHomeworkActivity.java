package com.example.hiroki.stdplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddHomeworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);
        getSupportActionBar().setTitle("Add homework");
    }
}
