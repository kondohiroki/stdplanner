package com.example.hiroki.stdplanner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class GradeFragment extends Fragment {


    public GradeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Grade");
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_grade, container, false);
        return rootView;
    }

}
