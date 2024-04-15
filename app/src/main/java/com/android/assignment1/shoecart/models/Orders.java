package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * Model class for Orders
 */
public class Orders implements Parcelable {
    ArrayList<Product> productList;

    String status;
    String orderDate;

    String orderId;
    String shippingAddress;
    String paymentMethod;

    /**
     * Constructor for Orders
     *
     * @param productList
     * @param status
     * @param orderDate
     * @param orderId
     * @param shippingAddress
     * @param paymentMethod
     * @return
     */
    public Orders(ArrayList<Product> productList, String status, String orderDate, String orderId, String shippingAddress, String paymentMethod) {
        this.productList = productList;
        this.status = status;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
    }

    /**
     * Constructor for Orders
     */
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

    /**
     * Constructor for Orders
     *
     * @param in
     */
    protected Orders(Parcel in) {
        productList = in.createTypedArrayList(Product.CREATOR);
        status = in.readString();
        orderDate = in.readString();
        orderId = in.readString();
        shippingAddress = in.readString();
        paymentMethod = in.readString();
    }

    /**
     * Method to get the product list
     *
     * @return productList
     */
    public ArrayList<Product> getProductList() {
        return productList;
    }

    /**
     * Method to set the product list
     *
     * @param productList
     */

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    /**
     * Method to get the status
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Method to set the status
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Method to get the order date
     *
     * @return orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * Method to set the order date
     *
     * @param orderDate
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Method to get the order id
     *
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Method to set the order id
     *
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Method to get the shipping address
     *
     * @return shippingAddress
     */

    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Method to set the shipping address
     *
     * @param shippingAddress
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Method to get the payment method
     *
     * @return paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Method to set the payment method
     *
     * @param paymentMethod
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Method to get the creator
     *
     * @return CREATOR
     */

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

    /**
     * Method to describe the contents
     *
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Method to write to the parcel
     *
     * @param dest
     * @param flags
     */
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
