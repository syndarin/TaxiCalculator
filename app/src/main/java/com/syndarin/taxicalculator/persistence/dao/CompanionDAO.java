package com.syndarin.taxicalculator.persistence.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.syndarin.taxicalculator.data.Companion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by syndarin on 8/26/15.
 */
public class CompanionDAO {

    public static long insert(SQLiteDatabase db, Companion companion){
        return db.insert(Companion.TABLE_NAME, null, companion.toContentValues());
    }

    public static int update(SQLiteDatabase db, Companion companion, int whereId){
        return db.update(Companion.TABLE_NAME, companion.toContentValues(), Companion.COLUMN_ID + " = ?", new String[]{String.valueOf(whereId)});
    }

    public static int delete(SQLiteDatabase db, int whereId){
        return db.delete(Companion.TABLE_NAME, Companion.COLUMN_ID + " = ?", new String[]{String.valueOf(whereId)});
    }

    public static List<Companion> getAll(SQLiteDatabase db){
        List<Companion> result = new ArrayList<>();

        Cursor c = db.rawQuery(Companion.SQL_SELECT_ALL, null);
        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                Companion companion = Companion.fromCursor(c);
                result.add(companion);
            }
        }
        c.close();

        return result;
    }

}
