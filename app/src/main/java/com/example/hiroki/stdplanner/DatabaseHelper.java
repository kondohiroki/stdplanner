package com.example.hiroki.stdplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NAME = "studentApp.db";

    // Table names
    private static final String TABLE_SUBJECT = "subject";
    private static final String TABLE_GRADE = "grade";
    private static final String TABLE_TIMETABLE = "timetable";
    private static final String TABLE_EXAM = "exam";
    private static final String TABLE_HOMEWORK = "homework";
    private static final String TABLE_REMINDER = "reminder";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // SUBJECT table - column names
    private static final String KEY_SUBJECT_NAME = "subject_name";
    private static final String KEY_SUBJECT_ROOM = "subject_room";
    private static final String KEY_SUBJECT_TEACHER = "subject_teacher";
    private static final String KEY_SUBJECT_SEMISTER = "subject_semister";
    private static final String KEY_SUBJECT_COLOR = "subject_color";

    // GRADE table - column names
    private static final String KEY_GRADE_VALUE = "grade_value";
    private static final String KEY_GRADE_SUBJECT = "grade_subject";
    private static final String KEY_GRADE_WEIGHT = "grade_weight";
    //private static final String KEY_GRADE_SEMISTER = "grade_semister";

    // TIMETABLE table - column names
    private static final String KEY_TIMETABLE_SUBJECT = "timetable_subject";
    private static final String KEY_TIMETABLE_DAY = "timetable_day";
    private static final String KEY_TIMETABLE_START = "timetable_start";
    private static final String KEY_TIMETABLE_END = "timetable_end";
    private static final String KEY_TIMETABLE_ROOM = "timetable_room";
    private static final String KEY_TIMETABLE_TEACHER = "timetable_teacher";
    private static final String KEY_TIMETABLE_COLOR = "timetable_color";

    // EXAM table - column names
    private static final String KEY_EXAM_TITLE = "exam_title";
    private static final String KEY_EXAM_SUBJECT = "exam_subject";
    private static final String KEY_EXAM_DATE = "exam_date";

    // HOMEWORK table - column names
    private static final String KEY_HOMEWORK_TITLE = "homework_title";
    private static final String KEY_HOMEWORK_SUBJECT = "homework_subject";
    private static final String KEY_HOMEWORK_DATE = "homework_date";

    // REMINDER table - column names
    private static final String KEY_REMINDER_TITLE = "reminder_title";
    private static final String KEY_REMINDER_DATE = "reminder_date";

    // Table Create Statements
    private static final String CREATE_TABLE_SUBJECT =
            "CREATE TABLE " + TABLE_SUBJECT + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    KEY_SUBJECT_NAME + " TEXT," +
                    KEY_SUBJECT_ROOM + " TEXT," +
                    KEY_SUBJECT_TEACHER + " TEXT," +
                    KEY_SUBJECT_SEMISTER + " INTEGER," +
                    KEY_SUBJECT_COLOR + " TEXT," +
                    KEY_CREATED_AT + " DATETIME)";

    //+ " FOREIGN KEY ("+TASK_CAT+") REFERENCES "+CAT_TABLE+"("+CAT_ID+"));";
    private static final String CREATE_TABLE_GRADE =
            "CREATE TABLE " + TABLE_GRADE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    KEY_GRADE_VALUE + " REAL," +
                    KEY_GRADE_SUBJECT + " INTEGER," +
                    KEY_GRADE_WEIGHT + " INTEGER," +
                    " FOREIGN KEY ("+KEY_GRADE_SUBJECT+") REFERENCES "+TABLE_SUBJECT+"("+KEY_ID+"))";

    private static final String CREATE_TABLE_TIMETABLE =
            "CREATE TABLE " + TABLE_TIMETABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    KEY_TIMETABLE_SUBJECT + " INTEGER," +
                    KEY_TIMETABLE_DAY + " TEXT," +
                    KEY_TIMETABLE_START + " TEXT," +
                    KEY_TIMETABLE_END + " TEXT," +
                    KEY_TIMETABLE_ROOM + " TEXT," +
                    KEY_TIMETABLE_TEACHER + " TEXT," +
                    KEY_TIMETABLE_COLOR + " TEXT," +
                    " FOREIGN KEY ("+KEY_TIMETABLE_SUBJECT+") REFERENCES "+TABLE_SUBJECT+"("+KEY_ID+"))";

    private static final String CREATE_TABLE_EXAM =
            "CREATE TABLE " + TABLE_EXAM + " (" +
                    KEY_EXAM_TITLE + " TEXT," +
                    KEY_EXAM_SUBJECT + " INTEGER," +
                    KEY_EXAM_DATE + " TEXT," +
                    " FOREIGN KEY ("+KEY_EXAM_SUBJECT+") REFERENCES "+TABLE_SUBJECT+"("+KEY_ID+"))";

    private static final String CREATE_TABLE_HOMEWORK =
            "CREATE TABLE " + TABLE_HOMEWORK + " (" +
                    KEY_HOMEWORK_TITLE + " TEXT," +
                    KEY_HOMEWORK_SUBJECT + " INTEGER," +
                    KEY_HOMEWORK_DATE + " TEXT," +
                    " FOREIGN KEY ("+KEY_HOMEWORK_SUBJECT+") REFERENCES "+TABLE_SUBJECT+"("+KEY_ID+"))";

    private static final String CREATE_TABLE_REMINDER =
            "CREATE TABLE " + TABLE_REMINDER + " (" +
                    KEY_REMINDER_TITLE + " TEXT," +
                    KEY_REMINDER_DATE + " TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SUBJECT);
        db.execSQL(CREATE_TABLE_GRADE);
        db.execSQL(CREATE_TABLE_TIMETABLE);
        db.execSQL(CREATE_TABLE_EXAM);
        db.execSQL(CREATE_TABLE_HOMEWORK);
        db.execSQL(CREATE_TABLE_REMINDER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRADE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMETABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOMEWORK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDER);

        onCreate(db);
    }

    public long addSubject(String name, String room, String teacher, int semister, String color){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SUBJECT_NAME, name);
        values.put(KEY_SUBJECT_ROOM, room);
        values.put(KEY_SUBJECT_TEACHER, teacher);
        values.put(KEY_SUBJECT_SEMISTER, semister);
        values.put(KEY_SUBJECT_COLOR, color);
        values.put(KEY_CREATED_AT, getDateTime());

        long row = db.insert(DatabaseHelper.TABLE_SUBJECT, null, values);
        db.close();
        return row;
    }

    public Cursor getAllSubject(){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {"id","subject_name","subject_room","subject_teacher",
                "subject_semister","subject_color"};
        Cursor cur = db.query(
          TABLE_SUBJECT,
          columns,
                null,
                null,
                null,
                null,
                null,
                null
        );
        return cur;
    }

    public Cursor getRecordSubject(String subjectName){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id"};
        String[] selectionArgs = { subjectName };

        Cursor cur = db.query(
                TABLE_SUBJECT,
                columns,
                KEY_SUBJECT_NAME + " =?",
                selectionArgs,
                null,
                null,
                null,
                null
        );

        return cur;
    }

    public long addGrade(double gradeValue, int subjectID, int weight){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GRADE_VALUE, gradeValue);
        values.put(KEY_GRADE_SUBJECT, subjectID);
        values.put(KEY_GRADE_WEIGHT, weight);

        long row = db.insert(DatabaseHelper.TABLE_GRADE, null, values);
        db.close();
        return row;
    }

    public Cursor getAllGrade(){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT grade_value, grade_weight, subject_name FROM grade INNER JOIN subject ON grade.grade_subject=subject.id";
        Cursor cur = db.rawQuery(sql,null,null);
        return cur;
    }

    public long addTimetable(int subjectID, String day, String start, String end,
                             String room, String teacher, String color){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TIMETABLE_SUBJECT, subjectID);
        values.put(KEY_TIMETABLE_DAY, day);
        values.put(KEY_TIMETABLE_START, start);
        values.put(KEY_TIMETABLE_END, end);
        values.put(KEY_TIMETABLE_ROOM, room);
        values.put(KEY_TIMETABLE_TEACHER, teacher);
        values.put(KEY_TIMETABLE_COLOR, color);

        long row = db.insert(DatabaseHelper.TABLE_TIMETABLE, null, values);
        db.close();
        return row;
    }

    public long addExam(String title, int subjectID, String date){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EXAM_TITLE,title);
        values.put(KEY_EXAM_SUBJECT,subjectID);
        values.put(KEY_EXAM_DATE,date);

        long row = db.insert(DatabaseHelper.TABLE_EXAM, null, values);
        db.close();
        return row;
    }

    public long addHomework(String title, int subjectID, String date){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HOMEWORK_TITLE,title);
        values.put(KEY_HOMEWORK_SUBJECT,subjectID);
        values.put(KEY_HOMEWORK_DATE,date);

        long row = db.insert(DatabaseHelper.TABLE_HOMEWORK, null, values);
        db.close();
        return row;
    }

    public long addReminder(String title, String date){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REMINDER_TITLE,title);
        values.put(KEY_REMINDER_DATE,date);

        long row = db.insert(DatabaseHelper.TABLE_REMINDER, null, values);
        db.close();
        return row;
    }

    private String getDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
