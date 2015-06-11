package com.syndarin.taxicalculator.persistence;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by syndarin on 6/4/15.
 */
@Database(name = DB.DB_NAME, version = DB.VERSION)
public class DB {

    public final static String DB_NAME = "TaxiDatabase";

    final static int VERSION = 1;

}
