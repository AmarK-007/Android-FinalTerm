package com.android.assignment1.shoecart.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.assignment1.shoecart.models.Wishlist;

import java.util.ArrayList;
import java.util.List;

/**
 * DataSource class for Wishlist
 */
public class WishlistDataSource {
    private DBHelper dbHelper;

    /**
     * Constructor for WishlistDataSource
     *
     * @param context
     * @return
     */
    public WishlistDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Wishlist table name
    public static final String TABLE_NAME = "wishlist";
    public static final String COLUMN_WISHLIST_ID = "wishlist_id";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_SIZE_ID = "size_id";
    public static final String COLUMN_USER_ID = "user_id";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_WISHLIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUCT_ID + " INTEGER,"
                    + COLUMN_SIZE_ID + " INTEGER,"
                    + COLUMN_USER_ID + " INTEGER,"
                    + "FOREIGN KEY(" + COLUMN_PRODUCT_ID + ") REFERENCES products(product_id),"
                    + "FOREIGN KEY(" + COLUMN_SIZE_ID + ") REFERENCES productsizes(size_id),"
                    + "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES users(user_id)"
                    + ")";

    /**
     * insertWishlist method
     *
     * @param wishlist
     * @return
     */
    public boolean insertWishlist(Wishlist wishlist) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, wishlist.getProductId());
        values.put(COLUMN_SIZE_ID, wishlist.getSizeId());
        values.put(COLUMN_USER_ID, wishlist.getUserId());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        if (result == -1) {
            return false; // Insertion failed
        } else {
            return true; // Insertion successful
        }
    }

    /**
     * getWishlist method
     *
     * @return
     */
    @SuppressLint("Range")
    public List<Wishlist> getAllWishlists() {
        List<Wishlist> wishlists = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Wishlist wishlist = new Wishlist();
                wishlist.setWishlistId(cursor.getInt(cursor.getColumnIndex(COLUMN_WISHLIST_ID)));
                wishlist.setProductId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));
                wishlist.setSizeId(cursor.getInt(cursor.getColumnIndex(COLUMN_SIZE_ID)));
                wishlist.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));

                wishlists.add(wishlist);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return wishlists;
    }

    /**
     * getWishlist method
     *
     * @param wishlist
     * @return
     */
    public void updateWishlist(Wishlist wishlist) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, wishlist.getProductId());
        values.put(COLUMN_SIZE_ID, wishlist.getSizeId());
        values.put(COLUMN_USER_ID, wishlist.getUserId());

        db.update(TABLE_NAME, values, COLUMN_WISHLIST_ID + " = ?",
                new String[]{String.valueOf(wishlist.getWishlistId())});
        db.close();
    }

    /**
     * deleteWishlist method
     *
     * @param wishlist
     * @return
     */
    public void deleteWishlist(Wishlist wishlist) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_WISHLIST_ID + " = ?",
                new String[]{String.valueOf(wishlist.getWishlistId())});
        db.close();
    }
}