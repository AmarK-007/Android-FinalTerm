package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class OrderDetail implements Parcelable {

    private int orderDetailId;
    private int orderId;
    private int productId;
    private int quantity;
    private String productSize;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, int orderId, int productId, int quantity, String productSize) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.productSize = productSize;
    }

    protected OrderDetail(Parcel in) {
        orderDetailId = in.readInt();
        orderId = in.readInt();
        productId = in.readInt();
        quantity = in.readInt();
        productSize = in.readString();
    }

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

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(orderDetailId);
        parcel.writeInt(orderId);
        parcel.writeInt(productId);
        parcel.writeInt(quantity);
        parcel.writeString(productSize);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

}
