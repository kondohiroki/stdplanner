package com.example.hiroki.stdplanner;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends Fragment {

    android.support.v4.app.FragmentTransaction ft;
    DatabaseHelper db;
    Cursor c;

    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    ArrayList datas;

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
        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        datas = new ArrayList<Data>();
        getData();

        listAdapter = new ListAdapter(datas);
        recyclerView.setAdapter(listAdapter);
        return rootView;
    }

    private void getData(){

        db = new DatabaseHelper(getActivity());
        c = db.getAllSubject();
        if (c != null && c.moveToFirst()) {
            do {
                String subject_name = c.getString(c.getColumnIndex("subject_name"));
                String subject_room = c.getString(c.getColumnIndex("subject_room"));
                String subject_teacher = c.getString(c.getColumnIndex("subject_teacher"));
                datas.add(new Data(subject_name,subject_room,subject_teacher));
            } while(c.moveToNext());
            db.close();
        }
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private ArrayList<Data> dataArrayList;
        public ListAdapter(ArrayList<Data> data){
            this.dataArrayList = data ;
        }
        public class ViewHolder extends RecyclerView.ViewHolder{

            TextView subjectH,roomH,teacherH;

            public ViewHolder(View itemView) {
                super(itemView);
                subjectH = (TextView)itemView.findViewById(R.id.cardSubjectShow);
                roomH = (TextView)itemView.findViewById(R.id.cardRoomShow);
                teacherH = (TextView)itemView.findViewById(R.id.cardTeacherShow);
            }
        }
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,
                    parent,false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position){
            holder.subjectH.setText(dataArrayList.get(position).getmTextSubject());
            holder.roomH.setText(dataArrayList.get(position).getmTextRoom());
            holder.teacherH.setText(dataArrayList.get(position).getmTextTeacher());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return dataArrayList.size();
        }
    }
}