package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model class for Wishlist
 */
public class Wishlist implements Parcelable {

    private int wishlistId;
    private int productId;
    private int sizeId;
    private int userId;

    public Wishlist() {
    }

    /**
     * Constructor for Wishlist
     */
    public Wishlist(int wishlistId, int productId, int sizeId, int userId) {
        this.wishlistId = wishlistId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.userId = userId;
    }

    public Wishlist(int productId, int sizeId, int userId) {
        this.productId = productId;
        this.sizeId = sizeId;
        this.userId = userId;
    }

    protected Wishlist(Parcel in) {
        wishlistId = in.readInt();
        productId = in.readInt();
        sizeId = in.readInt();
        userId = in.readInt();
    }

    /**
     * Creator for Wishlist
     */
    public static final Creator<Wishlist> CREATOR = new Creator<Wishlist>() {
        @Override
        public Wishlist createFromParcel(Parcel in) {
            return new Wishlist(in);
        }

        @Override
        public Wishlist[] newArray(int size) {
            return new Wishlist[size];
        }
    };

    /**
     * Describe contents
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * writeToParcel method
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(wishlistId);
        parcel.writeInt(productId);
        parcel.writeInt(sizeId);
        parcel.writeInt(userId);
    }

    // Getters and setters

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}