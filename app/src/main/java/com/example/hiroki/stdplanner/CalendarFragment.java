package com.example.hiroki.stdplanner;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    DatabaseHelper db;
    Cursor c;

    CalendarView calendarView;
    android.support.v4.app.FragmentTransaction ft;
    List<EventDay> events = new ArrayList<>();

    ArrayList datas;
    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 10001) && (resultCode == Activity.RESULT_OK)){
            ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
            db = new DatabaseHelper(getActivity());
            c = db.getAllReminder();
            if (c != null && c.moveToFirst()) {
                do {
                    int dd = c.getInt(c.getColumnIndex("reminder_day"));
                    int mm = c.getInt(c.getColumnIndex("reminder_month"));
                    int yy = c.getInt(c.getColumnIndex("reminder_year"));
                    System.out.println("dd mm yy"+dd+""+mm+""+yy);
                    Calendar ca = Calendar.getInstance();
                    ca.set(yy, mm, dd);
                    events.add(new EventDay(ca, R.drawable.ic_add_alert));
                    calendarView.setEvents(events);
                } while(c.moveToNext());
                db.close();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Calendar");
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);

        FloatingActionButton fabReminder =  rootView.findViewById(R.id.fab_reminder);
        FloatingActionButton fabExam =  rootView.findViewById(R.id.fab_exam);
        FloatingActionButton fabHomework =  rootView.findViewById(R.id.fab_homework);

        fabReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(getActivity(), AddReminderActivity.class);
                //startActivity(itn);
                startActivityForResult(itn,10001);
            }
        });

        fabExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(getActivity(), AddExamActivity.class);
                startActivity(itn);
            }
        });

        fabHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(getActivity(), AddHomeworkActivity.class);
                startActivity(itn);
            }
        });

        calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        calendarView.showCurrentMonthPage();

        db = new DatabaseHelper(getActivity());
        c = db.getAllReminder();
        if (c != null && c.moveToFirst()) {
            do {
                int dd = c.getInt(c.getColumnIndex("reminder_day"));
                int mm = c.getInt(c.getColumnIndex("reminder_month"));
                int yy = c.getInt(c.getColumnIndex("reminder_year"));
                Calendar ca = Calendar.getInstance();
                ca.set(yy, mm, dd);
                events.add(new EventDay(ca, R.drawable.ic_add_alert));
                calendarView.setEvents(events);
            } while(c.moveToNext());
            db.close();
        }



        return rootView;

    }
    public void getReminder(){
        db = new DatabaseHelper(getActivity());
        c = db.getAllReminder();
        if (c != null && c.moveToFirst()) {
            do {
                int dd = c.getInt(c.getColumnIndex("reminder_day"));
                int mm = c.getInt(c.getColumnIndex("reminder_month"));
                int yy = c.getInt(c.getColumnIndex("reminder_year"));
                Calendar ca = Calendar.getInstance();
                ca.set(yy, mm, dd);
                events.add(new EventDay(ca, R.drawable.ic_add_alert));
                calendarView.setEvents(events);
            } while(c.moveToNext());
            db.close();
        }

    }
}
