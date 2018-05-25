package com.example.hiroki.stdplanner;

public class Data {

    private String mTextSubject;
    private String mTextRoom;
    private String mTextTeacher;

    public Data(String subject,String room,String teacher){
        this.mTextSubject = subject;
        this.mTextRoom = room;
        this.mTextTeacher = teacher;
    }

    public String getmTextSubject() {
        return mTextSubject;
    }

    public String getmTextRoom() {
        return mTextRoom;
    }

    public String getmTextTeacher() {
        return mTextTeacher;
    }
}
