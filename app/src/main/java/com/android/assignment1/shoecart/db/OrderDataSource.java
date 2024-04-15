package com.android.assignment1.shoecart.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.assignment1.shoecart.models.Order;
import com.android.assignment1.shoecart.models.OrderDetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DataSource class for Order
 */
public class OrderDataSource {
    private DBHelper dbHelper;

    // Orders table name
    private static final String ORDERS_TABLE_NAME = "orders";
    private static final String ORDER_ID_COLUMN = "order_id";
    private static final String USER_ID_COLUMN = "user_id";
    private static final String TOTAL_AMOUNT_COLUMN = "total_amount";
    private static final String ORDER_DATE_COLUMN = "order_date";
    private static final String DELIVERY_DATE_COLUMN = "delivery_date";
    private static final String PAYMENT_METHOD_COLUMN = "payment_method";
    private static final String DELIVERY_STATUS_COLUMN = "delivery_status";
    private static final String RETURN_STATUS_COLUMN = "return_status";

    //  OrderDetails table name
    private static final String ORDER_DETAILS_TABLE_NAME = "orderdetails";
    private static final String ORDER_DETAIL_ID_COLUMN = "order_detail_id";
    private static final String PRODUCT_ID_COLUMN = "product_id";
    private static final String QUANTITY_COLUMN = "quantity";
    private static final String PRODUCT_SIZE_COLUMN = "product_size";

    public static final String CREATE_ORDERS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ORDERS_TABLE_NAME + "("
                    + ORDER_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + USER_ID_COLUMN + " INTEGER,"
                    + TOTAL_AMOUNT_COLUMN + " REAL,"
                    + ORDER_DATE_COLUMN + " DATETIME,"
                    + DELIVERY_DATE_COLUMN + " DATETIME,"
                    + PAYMENT_METHOD_COLUMN + " TEXT,"
                    + DELIVERY_STATUS_COLUMN + " TEXT,"
                    + RETURN_STATUS_COLUMN + " TEXT"
                    + ")";

    public static final String CREATE_ORDER_DETAILS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ORDER_DETAILS_TABLE_NAME + "("
                    + ORDER_DETAIL_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ORDER_ID_COLUMN + " INTEGER,"
                    + PRODUCT_ID_COLUMN + " INTEGER,"
                    + QUANTITY_COLUMN + " INTEGER,"
                    + PRODUCT_SIZE_COLUMN + " TEXT,"
                    + "FOREIGN KEY(" + ORDER_ID_COLUMN + ") REFERENCES " + ORDERS_TABLE_NAME + "(" + ORDER_ID_COLUMN + "),"
                    + "FOREIGN KEY(" + PRODUCT_ID_COLUMN + ") REFERENCES products(product_id)"
                    + ")";

    /**
     * Constructor for OrderDataSource
     *
     * @param context
     * @return
     */
    public OrderDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * insertOrder method
     *
     * @param order
     * @return
     */
    public boolean insertOrder(Order order) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("user_id", order.getUserId());
        values.put("total_amount", order.getTotalAmount());
        values.put("order_date", order.getOrderDate().getTime());
        values.put("delivery_date", order.getDeliveryDate().getTime());
        values.put("payment_method", order.getPaymentMethod());
        values.put("delivery_status", order.getDeliveryStatus());
        values.put("return_status", order.getReturnStatus());

        long orderId = db.insert(ORDERS_TABLE_NAME, null, values);

        if (orderId == -1) {
            return false; // Insertion failed
        } else {
            for (OrderDetail detail : order.getOrderDetails()) {
                ContentValues detailValues = new ContentValues();
                detailValues.put("order_id", orderId);
                detailValues.put("product_id", detail.getProductId());
                detailValues.put("quantity", detail.getQuantity());
                detailValues.put("product_size", detail.getProductSize());

                long detailId = db.insert(ORDER_DETAILS_TABLE_NAME, null, detailValues);

                if (detailId == -1) {
                    return false; // Insertion failed
                }
            }
            return true; // Insertion successful
        }
    }

    /**
     * getAllOrders method
     *
     * @return
     */
    @SuppressLint("Range")
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + ORDERS_TABLE_NAME;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setOrderId(cursor.getInt(cursor.getColumnIndex(ORDER_ID_COLUMN)));
                order.setUserId(cursor.getInt(cursor.getColumnIndex(USER_ID_COLUMN)));
                order.setTotalAmount(cursor.getDouble(cursor.getColumnIndex(TOTAL_AMOUNT_COLUMN)));
                order.setOrderDate(new Date(cursor.getLong(cursor.getColumnIndex(ORDER_DATE_COLUMN))));
                order.setDeliveryDate(new Date(cursor.getLong(cursor.getColumnIndex(DELIVERY_DATE_COLUMN))));
                order.setPaymentMethod(cursor.getString(cursor.getColumnIndex(PAYMENT_METHOD_COLUMN)));
                order.setDeliveryStatus(cursor.getString(cursor.getColumnIndex(DELIVERY_STATUS_COLUMN)));
                order.setReturnStatus(cursor.getString(cursor.getColumnIndex(RETURN_STATUS_COLUMN)));

                List<OrderDetail> orderDetails = new ArrayList<>();
                String selectDetailsQuery = "SELECT  * FROM " + ORDER_DETAILS_TABLE_NAME + " WHERE " + ORDER_ID_COLUMN + " = " + order.getOrderId();
                Cursor detailsCursor = db.rawQuery(selectDetailsQuery, null);

                if (detailsCursor.moveToFirst()) {
                    do {
                        OrderDetail detail = new OrderDetail();
                        detail.setOrderDetailId(detailsCursor.getInt(detailsCursor.getColumnIndex(ORDER_DETAIL_ID_COLUMN)));
                        detail.setProductId(detailsCursor.getInt(detailsCursor.getColumnIndex(PRODUCT_ID_COLUMN)));
                        detail.setQuantity(detailsCursor.getInt(detailsCursor.getColumnIndex(QUANTITY_COLUMN)));
                        detail.setProductSize(detailsCursor.getString(detailsCursor.getColumnIndex(PRODUCT_SIZE_COLUMN)));

                        orderDetails.add(detail);
                    } while (detailsCursor.moveToNext());
                }

                order.setOrderDetails(orderDetails);
                orders.add(order);
            } while (cursor.moveToNext());
        }

        return orders;
    }

    /**
     * getOrder method
     *
     * @param orderId
     * @return
     */
    @SuppressLint("Range")
    private Order getOrder(int orderId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(ORDERS_TABLE_NAME,
                null, ORDER_ID_COLUMN + "=?",
                new String[]{String.valueOf(orderId)},
                null, null, null);


        if (cursor != null && cursor.moveToFirst()) {
            Order order = new Order();
            order.setOrderId(cursor.getInt(cursor.getColumnIndex(ORDER_ID_COLUMN)));
            order.setUserId(cursor.getInt(cursor.getColumnIndex(USER_ID_COLUMN)));
            order.setTotalAmount(cursor.getDouble(cursor.getColumnIndex(TOTAL_AMOUNT_COLUMN)));
            order.setOrderDate(new Date(cursor.getLong(cursor.getColumnIndex(ORDER_DATE_COLUMN))));
            order.setDeliveryDate(new Date(cursor.getLong(cursor.getColumnIndex(DELIVERY_DATE_COLUMN))));
            order.setPaymentMethod(cursor.getString(cursor.getColumnIndex(PAYMENT_METHOD_COLUMN)));
            order.setDeliveryStatus(cursor.getString(cursor.getColumnIndex(DELIVERY_STATUS_COLUMN)));
            order.setReturnStatus(cursor.getString(cursor.getColumnIndex(RETURN_STATUS_COLUMN)));

            List<OrderDetail> orderDetails = new ArrayList<>();
            String selectDetailsQuery = "SELECT  * FROM " + ORDER_DETAILS_TABLE_NAME + " WHERE " + ORDER_ID_COLUMN + " = " + order.getOrderId();
            Cursor detailsCursor = db.rawQuery(selectDetailsQuery, null);

            if (detailsCursor.moveToFirst()) {
                do {
                    OrderDetail detail = new OrderDetail();
                    detail.setOrderDetailId(detailsCursor.getInt(detailsCursor.getColumnIndex(ORDER_DETAIL_ID_COLUMN)));
                    detail.setProductId(detailsCursor.getInt(detailsCursor.getColumnIndex(PRODUCT_ID_COLUMN)));
                    detail.setQuantity(detailsCursor.getInt(detailsCursor.getColumnIndex(QUANTITY_COLUMN)));
                    detail.setProductSize(detailsCursor.getString(detailsCursor.getColumnIndex(PRODUCT_SIZE_COLUMN)));

                    orderDetails.add(detail);
                } while (detailsCursor.moveToNext());
            }

            order.setOrderDetails(orderDetails);
            return order;
        } else {
            return null;
        }
    }

    /**
     * updateOrder method
     *
     * @param order
     * @return
     */
    public void updateOrder(Order order) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID_COLUMN, order.getUserId());
        values.put(TOTAL_AMOUNT_COLUMN, order.getTotalAmount());
        values.put(ORDER_DATE_COLUMN, order.getOrderDate().getTime());
        values.put(DELIVERY_DATE_COLUMN, order.getDeliveryDate().getTime());
        values.put(PAYMENT_METHOD_COLUMN, order.getPaymentMethod());
        values.put(DELIVERY_STATUS_COLUMN, order.getDeliveryStatus());
        values.put(RETURN_STATUS_COLUMN, order.getReturnStatus());

        db.update(ORDERS_TABLE_NAME, values, ORDER_ID_COLUMN + " = ?",
                new String[]{String.valueOf(order.getOrderId())});
    }

    /**
     * deleteOrder method
     *
     * @param order
     * @return
     */
    public void deleteOrder(Order order) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(ORDERS_TABLE_NAME, ORDER_ID_COLUMN + " = ?",
                new String[]{String.valueOf(order.getOrderId())});
        db.close();
    }
}