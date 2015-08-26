package com.syndarin.taxicalculator.persistence.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.syndarin.taxicalculator.persistence.DB;

/**
 * Created by syndarin on 6/22/15.
 */
@Table(databaseName = DB.DB_NAME)
public class Waypoint extends BaseModel {

    public final static String COLUMN_ID = "id";
    public final static String COLUMN_RIDE_ID = "ride_id";
    public final static String COLUMN_LATITUDE = "latitude";
    public final static String COLUMN_LONGITUDE = "longitude";

    @Column(name = COLUMN_ID)
    @PrimaryKey(autoincrement = true)
    int mId;

    @Column(name = COLUMN_RIDE_ID)
    int mRideId;

    @Column(name = COLUMN_LATITUDE)
    double mLatitude;

    @Column(name = COLUMN_LONGITUDE)
    double mLongitude;

}
