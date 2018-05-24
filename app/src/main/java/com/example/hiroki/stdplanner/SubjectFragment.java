package com.example.hiroki.stdplanner;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends Fragment {


    public SubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Subject");

        final View rootView = inflater.inflate(R.layout.fragment_subject, container, false);
        FloatingActionButton fab =  rootView.findViewById(R.id.fab_subject);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(getActivity(), AddSubjectActivity.class);
                startActivity(itn);
            }
        });
        // Inflate the layout for this fragment
        return rootView;

    }


    public void openAddSubject(View v){
        Intent itn = new Intent(getActivity(), AddSubjectActivity.class);
        startActivity(itn);
    }

}
