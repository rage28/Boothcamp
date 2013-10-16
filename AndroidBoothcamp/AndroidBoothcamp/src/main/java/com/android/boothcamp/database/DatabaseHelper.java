package com.android.boothcamp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.boothcamp.database.model.Scholar;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "boothcamp.sqlite";
    public static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + Scholar.TABLE_NAME + " (" + Scholar.INO + " varchar(10) primary key, " + Scholar.NAME + " varchar(70))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}