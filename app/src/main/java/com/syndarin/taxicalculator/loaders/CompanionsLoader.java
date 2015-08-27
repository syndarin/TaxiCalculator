package com.syndarin.taxicalculator.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.syndarin.taxicalculator.data.Companion;
import com.syndarin.taxicalculator.persistence.DBHelper;
import com.syndarin.taxicalculator.persistence.dao.CompanionDAO;

import java.util.List;

/**
 * Created by syndarin on 8/27/15.
 */
public class CompanionsLoader extends AsyncTaskLoader<List<Companion>> {

    private List<Companion> mCompanions;

    public CompanionsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mCompanions != null) {
            deliverResult(mCompanions);
        }

        if (mCompanions == null || takeContentChanged()) {
            forceLoad();
        }
    }

    @Override
    public List<Companion> loadInBackground() {
        SQLiteDatabase db = new DBHelper(getContext()).getReadableDatabase();
        List<Companion> companions = CompanionDAO.getAll(db);
        db.close();

        return companions;
    }

    @Override
    public void deliverResult(List<Companion> data) {
        if (isReset()) {
            return;
        }

        mCompanions = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        mCompanions = null;
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }
}
