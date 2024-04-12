package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {

    private int cartId;
    private int productId;
    private String productSize;
    private int quantity;
    private int userId;

    public Cart() {
    }

    public Cart(int productId, String productSize, int quantity, int userId) {
        this.productId = productId;
        this.productSize = productSize;
        this.quantity = quantity;
        this.userId = userId;
    }

    public Cart(int cartId, int productId, String productSize, int quantity, int userId) {
        this.cartId = cartId;
        this.productId = productId;
        this.productSize = productSize;
        this.quantity = quantity;
        this.userId = userId;
    }

    protected Cart(Parcel in) {
        cartId = in.readInt();
        productId = in.readInt();
        productSize = in.readString();
        quantity = in.readInt();
        userId = in.readInt();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(cartId);
        parcel.writeInt(productId);
        parcel.writeString(productSize);
        parcel.writeInt(quantity);
        parcel.writeInt(userId);
    }

    // Getters and setters

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}