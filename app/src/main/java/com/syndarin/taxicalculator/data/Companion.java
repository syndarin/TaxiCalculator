package com.syndarin.taxicalculator.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.syndarin.taxicalculator.util.Const;

/**
 * Created by syndarin on 8/26/15.
 */
public class Companion {

    public static String TABLE_NAME = "companions";

    public static String COLUMN_ID = "_id";
    public static String COLUMN_NAME = "name";
    public static String COLUMN_RIDES_TOGETHER = "rides_together";
    public static String COLUMN_UNPAID_AMOUNT = "unpaid_amount";
    public static String COLUMN_EMAIL = "email";
    public static String COLUMN_PHONE = "phone";

    public static String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text(" + Const.COMPANION_MAX_NAME_LENGTH + ") not null, "
            + COLUMN_RIDES_TOGETHER + " integer(5) default 0, "
            + COLUMN_UNPAID_AMOUNT + " real default 0, "
            + COLUMN_EMAIL + " text, "
            + COLUMN_PHONE + " text, "
            + ")";

    public static String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static String SQL_SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    public static Companion fromCursor(Cursor cursor){
        Companion companion = new Companion();

        companion.mId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        companion.mName = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME));
        companion.mRidesTogether = cursor.getInt(cursor.getColumnIndex(COLUMN_RIDES_TOGETHER));
        companion.mUnpaidAmount = cursor.getInt(cursor.getColumnIndex(COLUMN_UNPAID_AMOUNT));
        companion.mEmail = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
        companion.mPhone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));

        return companion;
    }

    private int mId;

    private int mName;

    private int mRidesTogether;

    private int mUnpaidAmount;

    private String mEmail;

    private String mPhone;

    public void setId(int id) {
        this.mId = id;
    }

    public void setName(int name) {
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

    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, mName);
        values.put(COLUMN_RIDES_TOGETHER, mRidesTogether);
        values.put(COLUMN_UNPAID_AMOUNT, mUnpaidAmount);
        values.put(COLUMN_EMAIL, mEmail);
        values.put(COLUMN_PHONE, mPhone);

        return values;
    }
}
