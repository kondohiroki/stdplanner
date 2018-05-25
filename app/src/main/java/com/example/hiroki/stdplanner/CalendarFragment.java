package com.example.hiroki.stdplanner;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    CalendarView calendarView;

    public CalendarFragment() {
        // Required empty public constructor
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
                startActivity(itn);
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
        return rootView;
    }
}
