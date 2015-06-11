package com.syndarin.taxicalculator.persistence.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.syndarin.taxicalculator.persistence.DB;

/**
 * Created by syndarin on 6/4/15.
 */
@Table(databaseName = DB.DB_NAME)
public class Companion extends BaseModel {

    public final static String COLUMN_ID = "id";
    public final static String COLUMN_NAME = "id";
    public final static String COLUMN_RIDES_TOGETHER = "id";
    public final static String COLUMN_OVERALL_SPENT = "id";
    public final static String COLUMN_HAS_UNPAID_RIDES = "id";

    @Column(name = COLUMN_ID)
    @PrimaryKey(autoincrement = true)
    long mId;

    @Column(name = COLUMN_NAME)
    String mName;

    @Column(name = COLUMN_RIDES_TOGETHER)
    int mRidesTogether;

    @Column(name = COLUMN_OVERALL_SPENT)
    int mOverallSpent;

    @Column(name = COLUMN_HAS_UNPAID_RIDES)
    boolean mHasUnpaidRides;

}
