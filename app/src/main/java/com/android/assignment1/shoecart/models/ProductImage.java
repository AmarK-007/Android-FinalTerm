package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model class for ProductImage
 */
public class ProductImage implements Parcelable {

    private int imageId;
    private int productId;
    private String imageUrl;

    /**
     * Constructor for ProductImage
     */
    public ProductImage() {
    }

    /**
     * Constructor for ProductImage
     *
     * @param imageId
     * @param productId
     * @param imageUrl
     */
    public ProductImage(int imageId, int productId, String imageUrl) {
        this.imageId = imageId;
        this.productId = productId;
        this.imageUrl = imageUrl;
    }

    /**
     * Constructor for ProductImage
     *
     * @param in
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * Constructor for ProductImage
     *
     * @param imageId
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /**
     * Constructor for ProductImage
     *
     * @return
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Constructor for ProductImage
     *
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Constructor for ProductImage
     *
     * @return
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Constructor for ProductImage
     *
     * @param imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Constructor for ProductImage
     *
     * @param in
     */
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

    /**
     * Constructor for ProductImage
     *
     * @param in
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Constructor for ProductImage
     *
     * @param parcel
     * @param i
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageId);
        parcel.writeInt(productId);
        parcel.writeString(imageUrl);
    }

    /**
     * Constructor for ProductImage
     *
     * @param in
     */
    protected ProductImage(Parcel in) {
        imageId = in.readInt();
        productId = in.readInt();
        imageUrl = in.readString();
    }
}
