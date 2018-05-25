package com.example.hiroki.stdplanner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyCardAdapter extends RecyclerView.Adapter<MyCardAdapter.ViewHolder>{

    private List<Data> mDataset;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        ViewHolder dataObjectHolder = new ViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.subject.setText(mDataset.get(position).getmTextSubject());
        holder.room.setText(mDataset.get(position).getmTextRoom());
        holder.teacher.setText(mDataset.get(position).getmTextTeacher());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public MyCardAdapter (List<Data> myDataset){
        mDataset =myDataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView subject,room,teacher;

        public ViewHolder(View itemView) {
            super(itemView);
            subject = (TextView)itemView.findViewById(R.id.cardSubjectShow);
            room = (TextView)itemView.findViewById(R.id.cardRoomShow);
            teacher = (TextView)itemView.findViewById(R.id.cardTeacherShow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
