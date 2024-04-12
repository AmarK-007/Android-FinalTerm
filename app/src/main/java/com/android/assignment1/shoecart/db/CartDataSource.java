package com.android.assignment1.shoecart.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.assignment1.shoecart.models.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartDataSource {
    private DBHelper dbHelper;

    public CartDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public static final String TABLE_NAME = "cart";
    public static final String COLUMN_CART_ID = "cart_id";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_PRODUCT_SIZE = "product_size";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_USER_ID = "user_id";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUCT_ID + " INTEGER,"
                    + COLUMN_PRODUCT_SIZE + " TEXT,"
                    + COLUMN_QUANTITY + " INTEGER,"
                    + COLUMN_USER_ID + " INTEGER,"
                    + "FOREIGN KEY(" + COLUMN_PRODUCT_ID + ") REFERENCES products(product_id),"
                    + "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES users(user_id)"
                    + ")";

    public boolean insertCart(Cart cart) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, cart.getProductId());
        values.put(COLUMN_PRODUCT_SIZE, cart.getProductSize());
        values.put(COLUMN_QUANTITY, cart.getQuantity());
        values.put(COLUMN_USER_ID, cart.getUserId());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        if (result == -1) {
            return false; // Insertion failed
        } else {
            return true; // Insertion successful
        }
    }

    @SuppressLint("Range")
    public List<Cart> getAllCarts() {
        List<Cart> carts = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Cart cart = new Cart();
                cart.setCartId(cursor.getInt(cursor.getColumnIndex(COLUMN_CART_ID)));
                cart.setProductId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));
                cart.setProductSize(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_SIZE)));
                cart.setQuantity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)));
                cart.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));

                carts.add(cart);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return carts;
    }

    @SuppressLint("Range")
    public List<Cart> getAllCartsForUser(String userID) {
        List<Cart> carts = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + COLUMN_USER_ID + " = " + userID;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Cart cart = new Cart();
                cart.setCartId(cursor.getInt(cursor.getColumnIndex(COLUMN_CART_ID)));
                cart.setProductId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));
                cart.setProductSize(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_SIZE)));
                cart.setQuantity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)));
                cart.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));

                carts.add(cart);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return carts;
    }

    public void updateCart(Cart cart) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, cart.getProductId());
        values.put(COLUMN_PRODUCT_SIZE, cart.getProductSize());
        values.put(COLUMN_QUANTITY, cart.getQuantity());
        values.put(COLUMN_USER_ID, cart.getUserId());

        db.update(TABLE_NAME, values, COLUMN_CART_ID + " = ?",
                new String[]{String.valueOf(cart.getCartId())});
        db.close();
    }

    public void deleteCart(Cart cart) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_CART_ID + " = ?",
                new String[]{String.valueOf(cart.getCartId())});
        db.close();
    }
}