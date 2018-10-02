package com.aqhmal.tempahanmakanan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tempahanmakanan.db";
    private static final int DATABASE_VERSION = 1;

    DBHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TempahanMakanan.TABLE + " (" +
                TempahanMakanan.ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                TempahanMakanan.NAME + " TEXT NOT NULL," +
                TempahanMakanan.USERNAME + " TEXT NOT NULL," +
                TempahanMakanan.PASSWORD + " TEXT NOT NULL)";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TempahanMakanan.TABLE);
        onCreate(sqLiteDatabase);
    }
}