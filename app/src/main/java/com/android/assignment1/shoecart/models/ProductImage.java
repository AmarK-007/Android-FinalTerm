package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductImage implements Parcelable {
    public static final String TABLE_NAME = "productimages";
    public static final String COLUMN_IMAGE_ID = "image_id";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_IMAGE_URL = "image_url";

    private int imageId;
    private int productId;
    private String imageUrl;

    public ProductImage() {
    }

    public ProductImage(int imageId, int productId, String imageUrl) {
        this.imageId = imageId;
        this.productId = productId;
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static final Creator<ProductImage> CREATOR = new Creator<ProductImage>() {
        @Override
        public ProductImage createFromParcel(Parcel in) {
            return new ProductImage(in);
        }

        @Override
        public ProductImage[] newArray(int size) {
            return new ProductImage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageId);
        parcel.writeInt(productId);
        parcel.writeString(imageUrl);
    }

    protected ProductImage(Parcel in) {
        imageId = in.readInt();
        productId = in.readInt();
        imageUrl = in.readString();
    }
}
