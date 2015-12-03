package com.syndarin.taxicalculator;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.syndarin.taxicalculator.data.Companion;
import com.syndarin.taxicalculator.persistence.DBHelper;
import com.syndarin.taxicalculator.persistence.dao.CompanionDAO;

import java.util.List;

public class CompanionDAOTest extends AndroidTestCase {

    DBHelper mHelper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext contextDelegate = new RenamingDelegatingContext(getContext(), "test_");
        mHelper = new DBHelper(contextDelegate);
    }

    public void testAddCompanion(){
        Companion companion = new Companion();
        companion.setName("Boris");

        SQLiteDatabase db = mHelper.getWritableDatabase();
        long insertId = CompanionDAO.insert(db, companion);
        db.close();

        assertTrue(insertId > 0);
    }

    public void testGetCompanions(){
        SQLiteDatabase db = mHelper.getReadableDatabase();

        List<Companion> companions = CompanionDAO.getAll(db);
        db.close();

        assertNotNull(companions.get(0));
    }

    public void testUpdateCompanion(){
        SQLiteDatabase db = mHelper.getWritableDatabase();

        List<Companion> companions = CompanionDAO.getAll(db);
        Companion companion = companions.get(0);
        companion.setName("Doris");

        int rowsAffected = CompanionDAO.update(db, companion, companion.getId());
        assertTrue(rowsAffected > 0);

        companions = CompanionDAO.getAll(db);
        companion = companions.get(0);
        assertEquals(companion.getName(), "Doris");

        db.close();
    }

    @Override
    protected void tearDown() throws Exception {
        mHelper.close();
        super.tearDown();
    }
}