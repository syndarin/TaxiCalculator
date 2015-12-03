package com.syndarin.taxicalculator.persistence.fixture;

import android.database.sqlite.SQLiteDatabase;

import com.syndarin.taxicalculator.data.IDbModel;

import java.util.Collection;

/**
 * Created by syndarin on 8/31/15.
 */
public abstract class CompanionFixture<IDbModel> {

    public void insert(SQLiteDatabase db){
        for(IDbModel model : getData()){

        }
    }

    protected abstract Collection<IDbModel> getData();

}
