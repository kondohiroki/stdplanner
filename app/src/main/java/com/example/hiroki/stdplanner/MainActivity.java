package com.example.hiroki.stdplanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_overview:
                    android.support.v4.app.FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.flmain,new OverviewFragment());
                    ft1.commit();
                    return true;
                case R.id.nav_calendar:
                    android.support.v4.app.FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.flmain,new CalendarFragment());
                    ft2.commit();
                    return true;
                case R.id.nav_timetable:
                    android.support.v4.app.FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                    ft3.replace(R.id.flmain,new TimetableFragment());
                    ft3.commit();
                    return true;
                case R.id.nav_subject:
                    android.support.v4.app.FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                    ft4.replace(R.id.flmain,new SubjectFragment());
                    ft4.commit();
                    return true;
                case R.id.nav_grade:
                    android.support.v4.app.FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                    ft5.replace(R.id.flmain,new GradeFragment());
                    ft5.commit();
                    return true;
            }
            return false;
        }
    };

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(getApplicationContext());
        db.addSubject("c programming2","78-301","Elvin",1,"#FFFFFF");
        //db.addGrade(4.0,1,3);
//        Calendar now = Calendar.getInstance();
//        now.set(Calendar.HOUR, 0);
//        now.set(Calendar.MINUTE, 0);
//        //.out.println(sdf.format(now.getTime()));
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        //db.addTimetable(1,"mon","10.00", "10.30", "78-301", "akara", "#FFFFFF");
        //db.addExam("digitalFinal",1,"2018-05-25");
        //db.addHomework("c-programming",1,"2018-06-22");
        //db.addReminder("birthday","2018-03-22");
        mTextMessage = (TextView) findViewById(R.id.message);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Defalut item
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flmain,new OverviewFragment());
        ft.commit();

        navigation.setSelectedItemId(R.id.nav_overview);
    }

}
