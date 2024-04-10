package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductSize implements Parcelable {
    public static final String TABLE_NAME = "productsizes";
    public static final String COLUMN_SIZE_ID = "size_id";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_SIZE_US = "size_us";
    public static final String COLUMN_QUANTITY = "quantity";

    private int sizeId;
    private int productId;
    private int sizeUs;
    private int quantity;

    public ProductSize() {
    }

    public ProductSize(int sizeId, int productId, int sizeUs, int quantity) {
        this.sizeId = sizeId;
        this.productId = productId;
        this.sizeUs = sizeUs;
        this.quantity = quantity;
    }

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
