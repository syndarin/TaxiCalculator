package com.syndarin.taxicalculator.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import com.syndarin.taxicalculator.util.Const;

/**
 * Created by syndarin on 8/26/15.
 */
public class Companion implements IDbModel {

    public final static Uri CONTENT_URI = Uri.parse("content://com.syndarin.taxicalculator/companion");

    public final static String TABLE_NAME = "companions";

    public final static String COLUMN_ID = "_id";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_RIDES_TOGETHER = "rides_together";
    public final static String COLUMN_UNPAID_AMOUNT = "unpaid_amount";
    public final static String COLUMN_EMAIL = "email";
    public final static String COLUMN_PHONE = "phone";

    public final static String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text(" + Const.COMPANION_MAX_NAME_LENGTH + ") not null, "
            + COLUMN_RIDES_TOGETHER + " integer(5) default 0, "
            + COLUMN_UNPAID_AMOUNT + " real default 0, "
            + COLUMN_EMAIL + " text, "
            + COLUMN_PHONE + " text"
            + ")";

    public final static String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public final static String SQL_SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    public final static Companion fromCursor(Cursor cursor){
        Companion companion = new Companion();

        companion.mId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        companion.mName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        companion.mRidesTogether = cursor.getInt(cursor.getColumnIndex(COLUMN_RIDES_TOGETHER));
        companion.mUnpaidAmount = cursor.getInt(cursor.getColumnIndex(COLUMN_UNPAID_AMOUNT));
        companion.mEmail = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
        companion.mPhone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));

        return companion;
    }

    private int mId;

    private String mName;

    private int mRidesTogether;

    private int mUnpaidAmount;

    private String mEmail;

    private String mPhone;

    public void setId(int id) {
        this.mId = id;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setRidesTogether(int ridesTogether) {
        this.mRidesTogether = ridesTogether;
    }

    public void setUnpaidAmount(int unpaidAmount) {
        this.mUnpaidAmount = unpaidAmount;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public void setPhone(String phone) {
        this.mPhone = phone;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getRidesTogether() {
        return mRidesTogether;
    }

    public int getUnpaidAmount() {
        return mUnpaidAmount;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPhone() {
        return mPhone;
    }

    @Override
    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, mName);
        values.put(COLUMN_RIDES_TOGETHER, mRidesTogether);
        values.put(COLUMN_UNPAID_AMOUNT, mUnpaidAmount);
        values.put(COLUMN_EMAIL, mEmail);
        values.put(COLUMN_PHONE, mPhone);

        return values;
    }
}
