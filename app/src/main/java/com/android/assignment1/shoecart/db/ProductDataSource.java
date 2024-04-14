package com.android.assignment1.shoecart.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.assignment1.shoecart.models.Product;
import com.android.assignment1.shoecart.models.ProductImage;
import com.android.assignment1.shoecart.models.ProductSize;

import java.util.ArrayList;
import java.util.List;

public class ProductDataSource {
    private DBHelper dbHelper;

    public ProductDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public static final String TABLE_NAME_PRODUCT = "products";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_SHIPPING_COST = "shipping_cost";
    public static final String COLUMN_IS_DELETED = "is_deleted";

    public static final String TABLE_NAME_PROD_IMAGE = "productimages";
    public static final String COLUMN_IMAGE_ID = "image_id";
    public static final String COLUMN_PROD_IMAGE_PRODUCT_ID = "product_id";
    public static final String COLUMN_IMAGE_URL = "image_url";


    public static final String TABLE_NAME_PROD_SIZE = "productsizes";
    public static final String COLUMN_SIZE_ID = "size_id";
    public static final String COLUMN_PROD_SIZE_PRODUCT_ID = "product_id";
    public static final String COLUMN_SIZE_US = "size_us";
    public static final String COLUMN_QUANTITY = "quantity";


    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PRODUCT + "("
                    + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_PRICE + " REAL,"
                    + COLUMN_SHIPPING_COST + " REAL,"
                    + COLUMN_IS_DELETED + " INTEGER DEFAULT 0 ,"
                    + COLUMN_CATEGORY + " TEXT,"
                    + ")";
    public static final String CREATE_TABLE_PROD_IMAGE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PROD_IMAGE + "("
                    + COLUMN_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PROD_IMAGE_PRODUCT_ID + " INTEGER,"
                    + COLUMN_IMAGE_URL + " TEXT"
                    + " FOREIGN KEY(" + COLUMN_PROD_IMAGE_PRODUCT_ID + ") REFERENCES " + TABLE_NAME_PRODUCT + "(" + COLUMN_PRODUCT_ID + ") "
                    + ")";
    public static final String CREATE_TABLE_PROD_SIZE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PROD_SIZE + "("
                    + COLUMN_SIZE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PROD_SIZE_PRODUCT_ID + " INTEGER,"
                    + COLUMN_SIZE_US + " INTEGER,"
                    + COLUMN_QUANTITY + " INTEGER"
                    + " FOREIGN KEY(" + COLUMN_PROD_SIZE_PRODUCT_ID + ") REFERENCES " + TABLE_NAME_PRODUCT + "(" + COLUMN_PRODUCT_ID + ") "
                    + ")";

    public void insertProduct(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, product.getProductId());
        values.put(COLUMN_CATEGORY, product.getCategory());
        values.put(COLUMN_TITLE, product.getTitle());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_SHIPPING_COST, product.getShippingCost());
        values.put(COLUMN_IS_DELETED, product.getIsDeleted());

        long insertID = db.insert(TABLE_NAME_PRODUCT, null, values);
        db.close();
        for (ProductSize productSize : product.getSizes())
            insertProductSize(insertID, productSize);
        for (ProductImage productImage : product.getImages())
            insertProductImage(insertID, productImage);
    }

    public void insertProductSize(long insertID, ProductSize productSize) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SIZE_ID, productSize.getSizeId());
        values.put(COLUMN_PROD_SIZE_PRODUCT_ID, productSize.getProductId());
        values.put(COLUMN_SIZE_US, productSize.getSizeUs());
        values.put(COLUMN_QUANTITY, productSize.getQuantity());

        db.insert(TABLE_NAME_PRODUCT, null, values);
        db.close();
    }

    public void insertProductImage(long insertID, ProductImage productImage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE_ID, productImage.getImageId());
        values.put(COLUMN_PROD_IMAGE_PRODUCT_ID, productImage.getProductId());
        values.put(COLUMN_IMAGE_URL, productImage.getImageUrl());

        db.insert(TABLE_NAME_PRODUCT, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public Product getProduct(int productId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME_PRODUCT,
                null, COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(productId)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Product product = new Product();
            product.setProductId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));
            product.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
            product.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
            product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
            product.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)));
            product.setShippingCost(cursor.getDouble(cursor.getColumnIndex(COLUMN_SHIPPING_COST)));
            product.setIsDeleted(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_DELETED)));

            product.setSizes(getProductSizes(productId));
            product.setImages(getProductImages(productId));

            cursor.close();
            return product;
        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME_PRODUCT;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setProductId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));
                product.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
                product.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)));
                product.setShippingCost(cursor.getDouble(cursor.getColumnIndex(COLUMN_SHIPPING_COST)));
                product.setIsDeleted(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_DELETED)));

                product.setSizes(getProductSizes(product.getProductId()));
                product.setImages(getProductImages(product.getProductId()));

                products.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return products;
    }

    public int updateProduct(Product product) {

        for (ProductSize productSize : product.getSizes())
            updateProductSize(productSize);
        for (ProductImage productImage : product.getImages())
            updateProductImage(productImage);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, product.getProductId());
        values.put(COLUMN_CATEGORY, product.getCategory());
        values.put(COLUMN_TITLE, product.getTitle());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_SHIPPING_COST, product.getShippingCost());
        values.put(COLUMN_IS_DELETED, product.getIsDeleted());
        return db.update(TABLE_NAME_PRODUCT, values, COLUMN_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(product.getProductId())});
    }

    public void deleteProduct(Product product) {
        for (ProductSize productSize : product.getSizes())
            deleteProductSize(productSize);
        for (ProductImage productImage : product.getImages())
            deleteProductImage(productImage);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME_PRODUCT, COLUMN_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(product.getProductId())});
        db.close();
    }

    @SuppressLint("Range")
    public List<ProductSize> getProductSizes(long productId) {
        List<ProductSize> productSizes = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_PROD_SIZE,
                null,
                COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(productId)},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                ProductSize productSize = new ProductSize();
                productSize.setSizeId(cursor.getInt(cursor.getColumnIndex(COLUMN_SIZE_ID)));
                productSize.setProductId(cursor.getInt(cursor.getColumnIndex(COLUMN_PROD_SIZE_PRODUCT_ID)));
                productSize.setSizeUs(cursor.getInt(cursor.getColumnIndex(COLUMN_SIZE_US)));
                productSize.setQuantity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)));

                productSizes.add(productSize);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return productSizes;
    }

    @SuppressLint("Range")
    public List<ProductImage> getProductImages(long productId) {
        List<ProductImage> productImages = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_PROD_IMAGE,
                null,
                COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(productId)},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                ProductImage productImage = new ProductImage();
                productImage.setImageId(cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE_ID)));
                productImage.setProductId(cursor.getInt(cursor.getColumnIndex(COLUMN_PROD_IMAGE_PRODUCT_ID)));
                productImage.setImageUrl(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL)));

                productImages.add(productImage);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return productImages;
    }

    public void updateProductSize(ProductSize productSize) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SIZE_US, productSize.getSizeUs());
        values.put(COLUMN_QUANTITY, productSize.getQuantity());
        values.put(COLUMN_PROD_SIZE_PRODUCT_ID, productSize.getProductId());
        values.put(COLUMN_SIZE_ID, productSize.getSizeId());
        values.put(COLUMN_QUANTITY, productSize.getQuantity());

        db.update(TABLE_NAME_PROD_SIZE, values, COLUMN_SIZE_ID + " = ?",
                new String[]{String.valueOf(productSize.getSizeId())});
        db.close();
    }

    public void updateProductImage(ProductImage productImage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PROD_IMAGE_PRODUCT_ID, productImage.getProductId());
        values.put(COLUMN_IMAGE_URL, productImage.getImageUrl());

        db.update(TABLE_NAME_PROD_IMAGE, values, COLUMN_IMAGE_ID + " = ?",
                new String[]{String.valueOf(productImage.getImageId())});
        db.close();
    }

    public void deleteProductSize(ProductSize productSize) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME_PROD_SIZE, COLUMN_SIZE_ID + " = ?",
                new String[]{String.valueOf(productSize.getSizeId())});
        db.close();
    }

    public void deleteProductImage(ProductImage productImage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME_PROD_IMAGE, COLUMN_IMAGE_ID + " = ?",
                new String[]{String.valueOf(productImage.getImageId())});
        db.close();
    }
}
