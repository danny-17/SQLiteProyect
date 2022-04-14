package com.example.sqliteproyect.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATA_VERSION =1;
    private static final String DATA_NOMBRE ="persona.db";
    public static final String TABLE_PERSONAS ="personas";

    public DbHelper(@Nullable Context context) {
        super(context, DATA_NOMBRE, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PERSONAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "email TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE " + TABLE_PERSONAS);
        onCreate(sqLiteDatabase);
    }
}
