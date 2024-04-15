package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * Model class for OrderDetail
 */
public class OrderDetail implements Parcelable {

    private int orderDetailId;
    private int orderId;
    private int productId;
    private int quantity;
    private String productSize;

    /**
     * Constructor for OrderDetail
     */
    public OrderDetail() {
    }

    /**
     * Constructor for OrderDetail
     *
     * @param orderDetailId
     * @param orderId
     * @param productId
     * @param quantity
     * @param productSize
     * @return
     */
    public OrderDetail(int orderDetailId, int orderId, int productId, int quantity, String productSize) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.productSize = productSize;
    }

    /**
     * Constructor for OrderDetail
     *
     * @param productId
     * @param quantity
     * @param productSize
     * @return
     */
    public OrderDetail(int productId, int quantity, String productSize) {
        this.productId = productId;
        this.quantity = quantity;
        this.productSize = productSize;
    }

    /**
     * Constructor for OrderDetail
     *
     * @param in
     * @return
     */
    protected OrderDetail(Parcel in) {
        orderDetailId = in.readInt();
        orderId = in.readInt();
        productId = in.readInt();
        quantity = in.readInt();
        productSize = in.readString();
    }

    /**
     * Creator for OrderDetail
     */
    public static final Creator<OrderDetail> CREATOR = new Creator<OrderDetail>() {
        @Override
        public OrderDetail createFromParcel(Parcel in) {
            return new OrderDetail(in);
        }

        @Override
        public OrderDetail[] newArray(int size) {
            return new OrderDetail[size];
        }
    };

    /**
     * writeToParcel method
     *
     * @param parcel
     */
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(orderDetailId);
        parcel.writeInt(orderId);
        parcel.writeInt(productId);
        parcel.writeInt(quantity);
        parcel.writeString(productSize);
    }

    /**
     * describeContents method
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * getOrderDetailId method
     *
     * @return
     */
    public int getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * setOrderDetailId method
     *
     * @param orderDetailId
     */
    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * getOrderId method
     *
     * @return
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * setOrderId method
     *
     * @param orderId
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * getProductId method
     *
     * @return
     */
    public int getProductId() {
        return productId;
    }

    /**
     * setProductId method
     *
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * getQuantity method
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * setQuantity method
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * getProductSize method
     *
     * @return
     */
    public String getProductSize() {
        return productSize;
    }

    /**
     * setProductSize method
     *
     * @param productSize
     */
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

}
