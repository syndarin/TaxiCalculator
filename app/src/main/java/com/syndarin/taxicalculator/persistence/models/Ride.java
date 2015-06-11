package com.syndarin.taxicalculator.persistence.models;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;
import com.syndarin.taxicalculator.persistence.DB;

import java.util.Date;
import java.util.List;

/**
 * Created by syndarin on 6/6/15.
 */
@Table(databaseName = DB.DB_NAME)
public class Ride extends BaseModel {

    public final static String COLUMN_ID = "id";
    public final static String COLUMN_DATE = "date";
    public final static String COLUMN_WAYPOINTS = "waypoints";
    public final static String COLUMN_COST = "cost";

    @Column(name = COLUMN_ID)
    @PrimaryKey(autoincrement = true)
    int mId;

    @Column(name = COLUMN_DATE)
    Date mDate;

    @Column(name = COLUMN_WAYPOINTS)
    List<LatLng> mWaypoints;

    List<CompanionShare> mCompanionsShare;

    @Column(name = COLUMN_COST)
    float mOverallCost;

    @OneToMany(methods = {OneToMany.Method.ALL})
    public List<CompanionShare> getCompanionsShare(){
        if(mCompanionsShare == null) {
            mCompanionsShare = new Select().from(CompanionShare.class).where(Condition.column(CompanionShare$Table.MRIDE_RIDE_ID).is(mId)).queryList();
        }

        return mCompanionsShare;
    }
}
