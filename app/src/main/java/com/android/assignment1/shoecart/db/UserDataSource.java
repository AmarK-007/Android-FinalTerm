package com.android.assignment1.shoecart.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.assignment1.shoecart.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataSource {
    private DBHelper dbHelper;

    public UserDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME, user.getName());
        values.put(User.COLUMN_EMAIL, user.getEmail());
        values.put(User.COLUMN_PASSWORD, user.getPassword());
        values.put(User.COLUMN_USERNAME, user.getUsername());
        values.put(User.COLUMN_PURCHASE_HISTORY, user.getPurchaseHistory());
        values.put(User.COLUMN_SHIPPING_ADDRESS_1, user.getShippingAddress1());
        values.put(User.COLUMN_SHIPPING_ADDRESS_2, user.getShippingAddress2());
        values.put(User.COLUMN_CITY, user.getCity());
        values.put(User.COLUMN_PROVINCE, user.getProvince());
        values.put(User.COLUMN_PINCODE, user.getPincode());

        db.insert(User.TABLE_NAME, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public User getUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_USER_ID, User.COLUMN_NAME, User.COLUMN_EMAIL, User.COLUMN_PASSWORD, User.COLUMN_USERNAME, /* Add the rest of the user fields... */},
                User.COLUMN_USERNAME + "=? AND " + User.COLUMN_PASSWORD + "=?",
                new String[]{username, password},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            User user = new User();
            user.setUserId(cursor.getInt(cursor.getColumnIndex(User.COLUMN_USER_ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(User.COLUMN_USERNAME)));
            user.setPurchaseHistory(cursor.getString(cursor.getColumnIndex(User.COLUMN_PURCHASE_HISTORY)));
            user.setShippingAddress1(cursor.getString(cursor.getColumnIndex(User.COLUMN_SHIPPING_ADDRESS_1)));
            user.setShippingAddress2(cursor.getString(cursor.getColumnIndex(User.COLUMN_SHIPPING_ADDRESS_2)));
            user.setCity(cursor.getString(cursor.getColumnIndex(User.COLUMN_CITY)));
            user.setProvince(cursor.getString(cursor.getColumnIndex(User.COLUMN_PROVINCE)));
            user.setPincode(cursor.getString(cursor.getColumnIndex(User.COLUMN_PINCODE)));

            cursor.close();
            return user;
        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + User.TABLE_NAME;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserId(cursor.getInt(cursor.getColumnIndex(User.COLUMN_USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(User.COLUMN_USERNAME)));
                user.setPurchaseHistory(cursor.getString(cursor.getColumnIndex(User.COLUMN_PURCHASE_HISTORY)));
                user.setShippingAddress1(cursor.getString(cursor.getColumnIndex(User.COLUMN_SHIPPING_ADDRESS_1)));
                user.setShippingAddress2(cursor.getString(cursor.getColumnIndex(User.COLUMN_SHIPPING_ADDRESS_2)));
                user.setCity(cursor.getString(cursor.getColumnIndex(User.COLUMN_CITY)));
                user.setProvince(cursor.getString(cursor.getColumnIndex(User.COLUMN_PROVINCE)));
                user.setPincode(cursor.getString(cursor.getColumnIndex(User.COLUMN_PINCODE)));

                users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return users;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME, user.getName());
        values.put(User.COLUMN_EMAIL, user.getEmail());
        values.put(User.COLUMN_PASSWORD, user.getPassword());
        values.put(User.COLUMN_USERNAME, user.getUsername());
        values.put(User.COLUMN_PURCHASE_HISTORY, user.getPurchaseHistory());
        values.put(User.COLUMN_SHIPPING_ADDRESS_1, user.getShippingAddress1());
        values.put(User.COLUMN_SHIPPING_ADDRESS_2, user.getShippingAddress2());
        values.put(User.COLUMN_CITY, user.getCity());
        values.put(User.COLUMN_PROVINCE, user.getProvince());
        values.put(User.COLUMN_PINCODE, user.getPincode());

        return db.update(User.TABLE_NAME, values, User.COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getUserId())});
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(User.TABLE_NAME, User.COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getUserId())});
        db.close();
    }
}
