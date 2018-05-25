package com.example.hiroki.stdplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddSubjectActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etRoom;
    EditText etTeacher;
    RadioGroup rgSemister;
    RadioButton radioButton;
    Button addBtn;
    String subjectTitle;
    String subjectRoom;
    String subjectTeacher;
    int semister;
    int selectedId;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        getSupportActionBar().setTitle("Add subject");

        etTitle = (EditText) findViewById(R.id.title);
        etRoom = (EditText) findViewById(R.id.room);
        etTeacher = (EditText) findViewById(R.id.teacher);
        rgSemister = (RadioGroup) findViewById(R.id.semister_group);
        addBtn = (Button)findViewById(R.id.addBotton_subject);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectTitle = etTitle.getText().toString();
                subjectRoom = etRoom.getText().toString();
                subjectTeacher = etTeacher.getText().toString();
                selectedId = rgSemister.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                semister = Integer.valueOf(radioButton.getText().toString());

                db = new DatabaseHelper(getApplicationContext());
                db.addSubject(subjectTitle,subjectRoom,subjectTeacher,semister,"#FFFFFF");
                
            }
        });

    }
}
