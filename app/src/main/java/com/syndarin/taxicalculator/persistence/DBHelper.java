package com.syndarin.taxicalculator.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.syndarin.taxicalculator.data.Companion;

/**
 * Created by syndarin on 8/26/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    private final static int VERSION = 1;
    private final static String NAME = "TC_DB";

    public DBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Companion.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // no need to process now
    }
}
