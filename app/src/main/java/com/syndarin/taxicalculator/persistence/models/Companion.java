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
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_RIDES_TOGETHER = "rides_together";
    public final static String COLUMN_OVERALL_SPENT = "overall_spent";
    public final static String COLUMN_HAS_UNPAID_RIDES = "has_unpaid_rides";

    @Column
    @PrimaryKey(autoincrement = true)
    int mId;

    @Column
    String mName;

    @Column
    int mRidesTogether;

    @Column
    int mOverallSpent;

    @Column
    boolean mHasUnpaidRides;

}
