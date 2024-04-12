package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.List;

public class Order implements Parcelable {

    private int orderId;
    private int userId;
    private double totalAmount;
    private Date orderDate;
    private Date deliveryDate;
    private String paymentMethod;
    private String deliveryStatus;
    private String returnStatus;
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(int orderId, int userId, double totalAmount, Date orderDate, Date deliveryDate, String paymentMethod, String deliveryStatus, String returnStatus, List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
        this.deliveryStatus = deliveryStatus;
        this.returnStatus = returnStatus;
        this.orderDetails = orderDetails;
    }

    public Order(int userId, double totalAmount, Date orderDate, Date deliveryDate, String paymentMethod, String deliveryStatus, String returnStatus, List<OrderDetail> orderDetails) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
        this.deliveryStatus = deliveryStatus;
        this.returnStatus = returnStatus;
        this.orderDetails = orderDetails;
    }

    protected Order(Parcel in) {
        orderId = in.readInt();
        userId = in.readInt();
        totalAmount = in.readDouble();
        orderDate = new Date(in.readLong());
        deliveryDate = new Date(in.readLong());
        paymentMethod = in.readString();
        deliveryStatus = in.readString();
        returnStatus = in.readString();
        orderDetails = in.createTypedArrayList(OrderDetail.CREATOR);
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeInt(orderId);
        parcel.writeInt(userId);
        parcel.writeDouble(totalAmount);
        parcel.writeLong(orderDate.getTime());
        parcel.writeLong(deliveryDate.getTime());
        parcel.writeString(paymentMethod);
        parcel.writeString(deliveryStatus);
        parcel.writeString(returnStatus);
        parcel.writeTypedList(orderDetails);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
