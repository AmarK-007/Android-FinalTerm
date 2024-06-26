package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * Model class for ProductSize
 */

public class ProductSize implements Parcelable {

    private int sizeId;
    private int productId;
    private int sizeUs;
    private int quantity;

/**
 * Constructor for ProductSize
 *
 * */
    public ProductSize() {
    }

/**
 * Constructor for ProductSize
 *
 * @param sizeId
 * @param productId
 * @param sizeUs
 * @param quantity
 * */
    public ProductSize(int sizeId, int productId, int sizeUs, int quantity) {
        this.sizeId = sizeId;
        this.productId = productId;
        this.sizeUs = sizeUs;
        this.quantity = quantity;
    }

    // Getter and Setter methods
    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSizeUs() {
        return sizeUs;
    }

    public void setSizeUs(int sizeUs) {
        this.sizeUs = sizeUs;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

/**
 * describeContents method
 * */

    public static final Creator<ProductSize> CREATOR = new Creator<ProductSize>() {
        @Override
        public ProductSize createFromParcel(Parcel in) {
            return new ProductSize(in);
        }

        @Override
        public ProductSize[] newArray(int size) {
            return new ProductSize[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(sizeId);
        parcel.writeInt(productId);
        parcel.writeInt(sizeUs);
        parcel.writeInt(quantity);
    }

    protected ProductSize(Parcel in) {
        sizeId = in.readInt();
        productId = in.readInt();
        sizeUs = in.readInt();
        quantity = in.readInt();
    }
}
