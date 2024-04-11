package com.android.assignment1.shoecart.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.assignment1.shoecart.models.User;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shoecart.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(User.CREATE_TABLE);
//        db.execSQL(UserDataSource.CREATE_TABLE);
//        db.execSQL(ProductDataSource.CREATE_TABLE);
//        db.execSQL(ProductDataSource.CREATE_TABLE_PROD_IMAGE);
//        db.execSQL(ProductDataSource.CREATE_TABLE_PROD_SIZE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + UserDataSource.TABLE_NAME);
        onCreate(db);
    }
}
