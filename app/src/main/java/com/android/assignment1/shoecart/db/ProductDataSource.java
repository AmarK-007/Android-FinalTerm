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

    public void insertProduct(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Product.COLUMN_PRODUCT_ID, product.getProductId());
        values.put(Product.COLUMN_TITLE, product.getTitle());
        values.put(Product.COLUMN_DESCRIPTION, product.getDescription());
        values.put(Product.COLUMN_PRICE, product.getPrice());
        values.put(Product.COLUMN_SHIPPING_COST, product.getShippingCost());
        values.put(Product.COLUMN_IS_DELETED, product.getIsDeleted());

        long insertID = db.insert(Product.TABLE_NAME, null, values);
        db.close();
        for (ProductSize productSize : product.getSizes())
            insertProductSize(insertID, productSize);
        for (ProductImage productImage : product.getImages())
            insertProductImage(insertID, productImage);
    }

    public void insertProductSize(long insertID, ProductSize productSize) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductSize.COLUMN_SIZE_ID, productSize.getSizeId());
        values.put(ProductSize.COLUMN_PRODUCT_ID, productSize.getProductId());
        values.put(ProductSize.COLUMN_SIZE_US, productSize.getSizeUs());
        values.put(ProductSize.COLUMN_QUANTITY, productSize.getQuantity());

        db.insert(ProductSize.TABLE_NAME, null, values);
        db.close();
    }

    public void insertProductImage(long insertID, ProductImage productImage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductImage.COLUMN_IMAGE_ID, productImage.getImageId());
        values.put(ProductImage.COLUMN_PRODUCT_ID, productImage.getProductId());
        values.put(ProductImage.COLUMN_IMAGE_URL, productImage.getImageUrl());

        db.insert(ProductImage.TABLE_NAME, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public Product getProduct(int productId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(Product.TABLE_NAME,
                null,
                Product.COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(productId)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Product product = new Product();
            product.setProductId(cursor.getInt(cursor.getColumnIndex(Product.COLUMN_PRODUCT_ID)));
            product.setTitle(cursor.getString(cursor.getColumnIndex(Product.COLUMN_TITLE)));
            product.setDescription(cursor.getString(cursor.getColumnIndex(Product.COLUMN_DESCRIPTION)));
            product.setPrice(cursor.getDouble(cursor.getColumnIndex(Product.COLUMN_PRICE)));
            product.setShippingCost(cursor.getDouble(cursor.getColumnIndex(Product.COLUMN_SHIPPING_COST)));
            product.setIsDeleted(cursor.getInt(cursor.getColumnIndex(Product.COLUMN_IS_DELETED)));

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

        String selectQuery = "SELECT  * FROM " + Product.TABLE_NAME;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setProductId(cursor.getInt(cursor.getColumnIndex(Product.COLUMN_PRODUCT_ID)));
                product.setTitle(cursor.getString(cursor.getColumnIndex(Product.COLUMN_TITLE)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(Product.COLUMN_DESCRIPTION)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndex(Product.COLUMN_PRICE)));
                product.setShippingCost(cursor.getDouble(cursor.getColumnIndex(Product.COLUMN_SHIPPING_COST)));
                product.setIsDeleted(cursor.getInt(cursor.getColumnIndex(Product.COLUMN_IS_DELETED)));

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
        values.put(Product.COLUMN_PRODUCT_ID, product.getProductId());
        values.put(Product.COLUMN_TITLE, product.getTitle());
        values.put(Product.COLUMN_DESCRIPTION, product.getDescription());
        values.put(Product.COLUMN_PRICE, product.getPrice());
        values.put(Product.COLUMN_SHIPPING_COST, product.getShippingCost());
        values.put(Product.COLUMN_IS_DELETED, product.getIsDeleted());
        return db.update(Product.TABLE_NAME, values, Product.COLUMN_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(product.getProductId())});
    }

    public void deleteProduct(Product product) {
        for (ProductSize productSize : product.getSizes())
            deleteProductSize(productSize);
        for (ProductImage productImage : product.getImages())
            deleteProductImage(productImage);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Product.TABLE_NAME, Product.COLUMN_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(product.getProductId())});
        db.close();
    }

    @SuppressLint("Range")
    public List<ProductSize> getProductSizes(long productId) {
        List<ProductSize> productSizes = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(ProductSize.TABLE_NAME,
                null,
                ProductSize.COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(productId)},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                ProductSize productSize = new ProductSize();
                productSize.setSizeId(cursor.getInt(cursor.getColumnIndex(ProductSize.COLUMN_SIZE_ID)));
                productSize.setProductId(cursor.getInt(cursor.getColumnIndex(ProductSize.COLUMN_PRODUCT_ID)));
                productSize.setSizeUs(cursor.getInt(cursor.getColumnIndex(ProductSize.COLUMN_SIZE_US)));
                productSize.setQuantity(cursor.getInt(cursor.getColumnIndex(ProductSize.COLUMN_QUANTITY)));

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
        Cursor cursor = db.query(ProductImage.TABLE_NAME,
                null,
                ProductImage.COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(productId)},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                ProductImage productImage = new ProductImage();
                productImage.setImageId(cursor.getInt(cursor.getColumnIndex(ProductImage.COLUMN_IMAGE_ID)));
                productImage.setProductId(cursor.getInt(cursor.getColumnIndex(ProductImage.COLUMN_PRODUCT_ID)));
                productImage.setImageUrl(cursor.getString(cursor.getColumnIndex(ProductImage.COLUMN_IMAGE_URL)));

                productImages.add(productImage);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return productImages;
    }

    public void updateProductSize(ProductSize productSize) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductSize.COLUMN_SIZE_US, productSize.getSizeUs());
        values.put(ProductSize.COLUMN_QUANTITY, productSize.getQuantity());

        db.update(ProductSize.TABLE_NAME, values, ProductSize.COLUMN_SIZE_ID + " = ?",
                new String[]{String.valueOf(productSize.getSizeId())});
        db.close();
    }

    public void updateProductImage(ProductImage productImage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductImage.COLUMN_IMAGE_URL, productImage.getImageUrl());

        db.update(ProductImage.TABLE_NAME, values, ProductImage.COLUMN_IMAGE_ID + " = ?",
                new String[]{String.valueOf(productImage.getImageId())});
        db.close();
    }

    public void deleteProductSize(ProductSize productSize) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(ProductSize.TABLE_NAME, ProductSize.COLUMN_SIZE_ID + " = ?",
                new String[]{String.valueOf(productSize.getSizeId())});
        db.close();
    }

    public void deleteProductImage(ProductImage productImage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(ProductImage.TABLE_NAME, ProductImage.COLUMN_IMAGE_ID + " = ?",
                new String[]{String.valueOf(productImage.getImageId())});
        db.close();
    }
}
