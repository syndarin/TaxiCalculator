package com.syndarin.taxicalculator.data;

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
}
