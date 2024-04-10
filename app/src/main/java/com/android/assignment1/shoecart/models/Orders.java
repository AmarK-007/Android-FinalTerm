package com.android.assignment1.shoecart.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Orders implements Serializable {
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
}
