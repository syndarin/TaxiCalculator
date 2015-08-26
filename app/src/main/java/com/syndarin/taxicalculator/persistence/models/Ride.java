package com.syndarin.taxicalculator.persistence.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.syndarin.taxicalculator.persistence.DB;

import java.util.Date;
import java.util.List;

/**
 * Created by syndarin on 6/6/15.
 */
@ModelContainer
@Table(databaseName = DB.DB_NAME)
public class Ride extends BaseModel {

    public final static String COLUMN_ID = "id";
    public final static String COLUMN_DATE = "date";
    public final static String COLUMN_COST = "total_cost";

    @Column(name = COLUMN_ID)
    @PrimaryKey(autoincrement = true)
    int mId;

    @Column(name = COLUMN_DATE)
    Date mDate;

    List<Waypoint> mWaypoints;

    List<CompanionShare> mCompanionsShare;

    @Column(name = COLUMN_COST)
    float mTotalCost;

    @OneToMany(methods = {OneToMany.Method.ALL})
    public List<CompanionShare> getCompanionsShare(){
        if(mCompanionsShare == null) {
            mCompanionsShare = new Select().from(CompanionShare.class).where(Condition.column(CompanionShare.COLUMN_RIDE).is(mId)).queryList();
        }

        return mCompanionsShare;
    }

    @OneToMany(methods = {OneToMany.Method.ALL})
    public List<Waypoint> getWaypoints(){
        if(mWaypoints == null){
            mWaypoints = new Select().from(Waypoint.class).where(Condition.column(Waypoint.COLUMN_RIDE_ID).is(mId)).queryList();
        }

        return mWaypoints;
    }
}
