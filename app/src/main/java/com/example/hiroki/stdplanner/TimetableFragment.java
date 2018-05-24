package com.example.hiroki.stdplanner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimetableFragment extends Fragment {


    public TimetableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Timetable");
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_timetable, container, false);
        return rootView;
    }

}
