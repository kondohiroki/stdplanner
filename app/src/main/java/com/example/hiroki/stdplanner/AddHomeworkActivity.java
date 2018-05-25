package com.example.hiroki.stdplanner;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.TimeZone;

public class AddHomeworkActivity extends AppCompatActivity {

    TextView tv;
    ImageButton bt,addBT,cancleBT;
    Calendar calendar;
    int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);
        getSupportActionBar().setTitle("Add homework");
        tv = (TextView) findViewById(R.id.showDate);
        bt = (ImageButton) findViewById(R.id.calendarPicker);

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
    }
}
