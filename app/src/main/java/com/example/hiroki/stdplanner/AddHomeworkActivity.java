package com.example.hiroki.stdplanner;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class AddHomeworkActivity extends AppCompatActivity {

    TextView tv;
    ImageButton bt,addBT,cancleBT;
    Calendar calendar;
    int day,month,year;

    DatabaseHelper db;
    Cursor c;
    final List<String> spinList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);
        getSupportActionBar().setTitle("Add homework");
        tv = (TextView) findViewById(R.id.showDate_Homework);
        bt = (ImageButton) findViewById(R.id.cPicker_Homework);

        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        month+=1;

        tv.setText(day+"/"+month+"/"+year);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddHomeworkActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker datePicker, int yeaR, int monthOfYear, int dayOfMonth) {
                        monthOfYear+=1;
                        tv.setText(dayOfMonth+"/"+monthOfYear+"/"+yeaR);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.subject_Homework);
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
}
