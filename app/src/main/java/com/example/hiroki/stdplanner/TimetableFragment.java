package com.example.hiroki.stdplanner;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.eunsiljo.timetablelib.data.TimeData;
import com.github.eunsiljo.timetablelib.data.TimeTableData;
import com.github.eunsiljo.timetablelib.view.TimeTableView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimetableFragment extends Fragment {

    private TimeTableView timeTable;

    private List<String> mTitles = Arrays.asList("Korean", "English", "Math", "Science", "Physics", "Chemistry", "Biology");
    private List<String> mShortHeaders = Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
    private ArrayList<TimeTableData> mShortSamples = new ArrayList<>();
    private long mNow = 0;
    public TimetableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Timetable");
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_timetable, container, false);
        timeTable = rootView.findViewById(R.id.timeTable);
        initData();
        return rootView;
    }

    private void initData(){
        timeTable.setStartHour(1);
        timeTable.setShowHeader(true);
        timeTable.setTableMode(TimeTableView.TableMode.SHORT);

        DateTime now = DateTime.now();
        mNow = now.withTimeAtStartOfDay().getMillis();

        timeTable.setTimeTable(mNow, getSamples(mNow,mShortHeaders,mTitles));

    }

    private ArrayList<TimeTableData> getSamples(long date, List<String> headers, List<String> titles){
        TypedArray colors_table = getResources().obtainTypedArray(R.array.colors_table);
        TypedArray colors_table_light = getResources().obtainTypedArray(R.array.colors_table_light);
        ArrayList<TimeTableData> tables = new ArrayList<>();
        for(int i=0; i<headers.size(); i++){
            ArrayList<TimeData> values = new ArrayList<>();
            DateTime start = new DateTime(date);
            DateTime end = start.plusMinutes((int)((Math.random() * 10) + 1) * 30);
            for(int j=0; j<titles.size(); j++){
                int color = colors_table_light.getResourceId(j, 0);
                int textColor = R.color.black;
                //TEST
                if(headers.size() == 2 && i == 1){
                    color = colors_table.getResourceId(j, 0);
                    textColor = R.color.white;
                }
                TimeData timeData = new TimeData(j, titles.get(j), color, textColor, start.getMillis(), end.getMillis());
                //TEST
                if(headers.size() == 2 && j == 2){
                    timeData.setShowError(true);
                }
                values.add(timeData);
                start = end.plusMinutes((int)((Math.random() * 10) + 1) * 10);
                end = start.plusMinutes((int)((Math.random() * 10) + 1) * 30);
            }
            tables.add(new TimeTableData(headers.get(i), values));
        }
        return tables;
    }

    private void initShortSamples(){

        //TEST
        ArrayList<TimeData> values = new ArrayList<>();
        values.add(new TimeData(0, "Korean", R.color.color_table_1_light, getMillis("2017-11-10 04:00:00"), getMillis("2017-11-10 05:00:00")));
        values.add(new TimeData(1, "English", R.color.color_table_2_light, getMillis("2017-11-10 07:00:00"), getMillis("2017-11-10 08:00:00")));

        ArrayList<TimeData> values2 = new ArrayList<>();
        values2.add(new TimeData(0, "Korean", R.color.color_table_1_light, getMillis("2017-11-10 03:00:00"), getMillis("2017-11-10 06:00:00")));
        values2.add(new TimeData(1, "English", R.color.color_table_2_light, getMillis("2017-11-10 07:30:00"), getMillis("2017-11-10 08:30:00")));
        values2.add(new TimeData(2, "Math", R.color.color_table_3_light, getMillis("2017-11-10 11:40:00"), getMillis("2017-11-10 11:45:00")));
        values2.add(new TimeData(3, "Science", R.color.color_table_4_light, getMillis("2017-11-10 18:00:00"), getMillis("2017-11-10 18:10:00")));
        values2.add(new TimeData(4, "Physics", R.color.color_table_5_light, getMillis("2017-11-10 20:00:00"), getMillis("2017-11-10 21:30:00")));
        values2.add(new TimeData(5, "Chemistry", R.color.color_table_6_light, getMillis("2017-11-10 21:31:00"), getMillis("2017-11-10 22:45:00")));
        values2.add(new TimeData(6, "Biology", R.color.color_table_7_light, getMillis("2017-11-10 23:00:00"), getMillis("2017-11-11 02:30:00")));
        ArrayList<TimeTableData> tables = new ArrayList<>();
        tables.add(new TimeTableData("Sun", values));
        tables.add(new TimeTableData("Mon", values2));
        tables.add(new TimeTableData("Tue", values));
        tables.add(new TimeTableData("Wed", values2));
        tables.add(new TimeTableData("Thu", values));
        tables.add(new TimeTableData("Fri", values2));
        tables.add(new TimeTableData("Sat", values));
        mShortSamples.addAll(tables);

    }
    private long getMillis(String day){
        DateTime date = getDateTimePattern().parseDateTime(day);
        return date.getMillis();
    }



    private DateTimeFormatter getDateTimePattern(){

        return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    }


}
