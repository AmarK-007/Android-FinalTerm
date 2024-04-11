package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Orders implements Parcelable {
 ArrayList<Product> productList;

    String status;
    String orderDate;

    String orderId;
    String shippingAddress;
    String paymentMethod;

    public Orders(ArrayList<Product> productList, String status, String orderDate, String orderId, String shippingAddress, String paymentMethod) {
        this.productList = productList;
        this.status = status;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
    }

    public static final Creator<Orders> CREATOR = new Creator<Orders>() {
        @Override
        public Orders createFromParcel(Parcel in) {
            return new Orders(in);
        }

        @Override
        public Orders[] newArray(int size) {
            return new Orders[size];
        }
    };

    protected Orders(Parcel in) {
        productList = in.createTypedArrayList(Product.CREATOR);
        status = in.readString();
        orderDate = in.readString();
        orderId = in.readString();
        shippingAddress = in.readString();
        paymentMethod = in.readString();
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }



    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "productList=" + productList +
                ", status='" + status + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderId='" + orderId + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeTypedList(productList);
        dest.writeString(status);
        dest.writeString(orderDate);
        dest.writeString(orderId);
        dest.writeString(shippingAddress);
        dest.writeString(paymentMethod);
    }
}
