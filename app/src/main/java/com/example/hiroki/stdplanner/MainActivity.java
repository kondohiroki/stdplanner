package com.example.hiroki.stdplanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
