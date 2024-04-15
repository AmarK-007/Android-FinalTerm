package com.android.assignment1.shoecart.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.assignment1.shoecart.models.User;
import com.android.assignment1.shoecart.utils.Constants;

/**
 * DBHelper class
 */
public class DBHelper extends SQLiteOpenHelper {
    /**
     * Constructor for DBHelper
     *
     * @param context
     * @return
     */
    public DBHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    /**
     * onCreate method
     *
     * @param db
     * @return
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(User.CREATE_TABLE);
//        db.execSQL(UserDataSource.CREATE_TABLE);
//        db.execSQL(ProductDataSource.CREATE_TABLE);
//        db.execSQL(ProductDataSource.CREATE_TABLE_PROD_IMAGE);
//        db.execSQL(ProductDataSource.CREATE_TABLE_PROD_SIZE);
    }

    /**
     * onUpgrade method
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     * @return
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + UserDataSource.TABLE_NAME);
        onCreate(db);
    }
}
