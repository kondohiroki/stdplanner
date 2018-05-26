package com.example.hiroki.stdplanner;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddGradeActivity extends AppCompatActivity {


    EditText eTgradeValue;
    int gradeSubjectID;
    EditText eTgradeWeight;
    RadioGroup rgSemister;
    RadioButton radioButton;
    Button addBtn;
    double gradeValue;
    String gradeSubject;
    int gradeWeight;
    int selectedId;

    DatabaseHelper db;
    Cursor c;
    final List<String> spinList = new ArrayList<>();

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        finishAndRemoveTask();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);
        getSupportActionBar().setTitle("Add grade");

        eTgradeValue = (EditText)findViewById(R.id.title_Grade);
        eTgradeWeight = (EditText)findViewById(R.id.weight_Grade);
        rgSemister = (RadioGroup)findViewById(R.id.semister_group_Grade);
        selectedId = rgSemister.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(selectedId);
        addBtn = (Button) findViewById(R.id.addBotton_Grade);

        Spinner spinner = (Spinner) findViewById(R.id.subject_Grade);
        getData();
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,spinList){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0){
                    return false;
                }else {
                    return true;
                }
            }
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent){
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0){
                    tv.setTextColor(Color.GRAY);
                }else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gradeSubject = String.valueOf(adapterView.getItemIdAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gradeValue = Double.valueOf(eTgradeValue.getText().toString());
                gradeWeight = Integer.valueOf(eTgradeWeight.getText().toString());
                db = new DatabaseHelper(getApplicationContext());
                c = db.getRecordSubject(gradeSubject);
                c.moveToFirst();
                //int id = c.getInt(c.getColumnIndex("id"));
                //System.out.println("id=>>>"+id);
                db.addGrade(gradeValue,1,gradeWeight);

                onBackPressed();
            }
        });


    }

    private void getData(){
        spinList.clear();
        db = new DatabaseHelper(this);
        c = db.getAllSubject();
        spinList.add("Select Subject");
        if (c != null && c.moveToFirst()) {
            do {
                String subject_name = c.getString(c.getColumnIndex("subject_name"));
                spinList.add(subject_name);
            } while(c.moveToNext());
            db.close();
        }
    }
    private void addGrade(){
        DatabaseHelper db2 = new DatabaseHelper(this);
        //gradeSubjectID = db2.getRecordSubject(gradeSubject);
        long row = db2.addGrade(gradeValue,gradeSubjectID,gradeWeight);
        db2.close();
    }
}
