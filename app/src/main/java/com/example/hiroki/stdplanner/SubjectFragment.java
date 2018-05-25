package com.example.hiroki.stdplanner;


import android.app.Activity;
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

    android.support.v4.app.FragmentTransaction ft;
    public SubjectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 10001) && (resultCode == Activity.RESULT_OK)){
            ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }
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
                startActivityForResult(itn,10001);
            }
        });
        // Inflate the layout for this fragment
        return rootView;

    }


}
