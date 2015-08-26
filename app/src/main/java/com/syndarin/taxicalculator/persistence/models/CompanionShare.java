package com.syndarin.taxicalculator.persistence.models;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;
import com.syndarin.taxicalculator.persistence.DB;

/**
 * Created by syndarin on 6/6/15.
 */
@Table(databaseName = DB.DB_NAME)
public class CompanionShare extends BaseModel {

    public final static String COLUMN_ID = "id";
    public final static String COLUMN_RIDE = "ride_id";
    public final static String COLUMN_COMPANION = "companion_id";
    public final static String COLUMN_WP_JOINED = "wp_joined";
    public final static String COLUMN_WP_LEAVED = "wp_leaved";
    public final static String COLUMN_COST_SHARE = "cost_share";
    public final static String COLUMN_PAID = "paid";


    @Column
    @PrimaryKey(autoincrement = true)
    int mId;

    @Column
    int mRideId;

    @Column
    @ForeignKey(references = {@ForeignKeyReference(columnName = COLUMN_COMPANION, columnType = Integer.class, foreignColumnName = Companion.COLUMN_ID)}, saveForeignKeyModel = false)
    Companion mCompanion;

    @Column
    int mWaypointJoined;

    @Column
    int mWaypointLeaved;

    @Column
    float mCostShare;

    @Column
    boolean mPaid;

}
