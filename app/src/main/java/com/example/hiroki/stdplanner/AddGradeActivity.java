package com.example.hiroki.stdplanner;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddGradeActivity extends AppCompatActivity {

    DatabaseHelper db;
    Cursor c;
    List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);
        Spinner spinner = (Spinner) findViewById(R.id.subject_Grade);


    }
    private void getData(){

        db = new DatabaseHelper(this);
        c = db.getAllSubject();
        datas.add("Select Subject");
        if (c != null && c.moveToFirst()) {
            do {
                String subject_name = c.getString(c.getColumnIndex("subject_name"));
                datas.add(subject_name);
            } while(c.moveToNext());
            db.close();
        }
    }
}
