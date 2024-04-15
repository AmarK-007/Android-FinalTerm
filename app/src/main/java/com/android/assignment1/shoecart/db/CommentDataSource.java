package com.android.assignment1.shoecart.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.assignment1.shoecart.models.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DataSource class for Comment
 */
public class CommentDataSource {
    private DBHelper dbHelper;

    /**
     * Constructor for CommentDataSource
     *
     * @param context
     * @return
     */
    public CommentDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Comment table name
    public static final String TABLE_NAME = "comments";
    public static final String COLUMN_COMMENT_ID = "comment_id";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_IMAGE_ID = "image_id";
    public static final String COLUMN_IMAGE_URL = "image_url";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_POSTED_ON = "posted_on";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_COMMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUCT_ID + " INTEGER,"
                    + COLUMN_USER_ID + " INTEGER,"
                    + COLUMN_RATING + " INTEGER,"
                    + COLUMN_IMAGE_ID + " INTEGER,"
                    + COLUMN_IMAGE_URL + " TEXT,"
                    + COLUMN_COMMENT + " TEXT,"
                    + COLUMN_POSTED_ON + " DATETIME,"
                    + "FOREIGN KEY(" + COLUMN_PRODUCT_ID + ") REFERENCES products(product_id),"
                    + "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES users(user_id)"
                    + ")";

    /**
     * insertComment method
     *
     * @param comment
     * @return
     */
    public boolean insertComment(Comment comment) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, comment.getProductId());
        values.put(COLUMN_USER_ID, comment.getUserId());
        values.put(COLUMN_RATING, comment.getRating());
        values.put(COLUMN_IMAGE_ID, comment.getImageId());
        values.put(COLUMN_IMAGE_URL, comment.getImageUrl());
        values.put(COLUMN_COMMENT, comment.getComment());
        values.put(COLUMN_POSTED_ON, comment.getPostedOn().getTime());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        if (result == -1) {
            return false; // Insertion failed
        } else {
            return true; // Insertion successful
        }
    }

    /**
     * getComment method
     *
     * @param commentId
     * @return
     */
    @SuppressLint("Range")
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Comment comment = new Comment();
                comment.setCommentId(cursor.getInt(cursor.getColumnIndex(COLUMN_COMMENT_ID)));
                comment.setProductId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));
                comment.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));
                comment.setRating(cursor.getInt(cursor.getColumnIndex(COLUMN_RATING)));
                comment.setImageId(cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE_ID)));
                comment.setImageUrl(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL)));
                comment.setComment(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)));
                comment.setPostedOn(new Date(cursor.getLong(cursor.getColumnIndex(COLUMN_POSTED_ON))));

                comments.add(comment);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return comments;
    }

    /**
     * getComment method
     *
     * @param comment
     * @return
     */
    public void updateComment(Comment comment) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, comment.getProductId());
        values.put(COLUMN_USER_ID, comment.getUserId());
        values.put(COLUMN_RATING, comment.getRating());
        values.put(COLUMN_IMAGE_ID, comment.getImageId());
        values.put(COLUMN_IMAGE_URL, comment.getImageUrl());
        values.put(COLUMN_COMMENT, comment.getComment());
        values.put(COLUMN_POSTED_ON, comment.getPostedOn().getTime());

        db.update(TABLE_NAME, values, COLUMN_COMMENT_ID + " = ?",
                new String[]{String.valueOf(comment.getCommentId())});
    }

    /**
     * deleteComment method
     *
     * @param comment
     * @return
     */
    public void deleteComment(Comment comment) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_COMMENT_ID + " = ?",
                new String[]{String.valueOf(comment.getCommentId())});
        db.close();
    }
}