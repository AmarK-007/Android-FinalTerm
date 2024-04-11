package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Product implements Parcelable {

    private int productId;
    private String title;
    private String description;
    private double price;
    private double shippingCost;
    private int isDeleted;

    private List<ProductSize> sizes;
    private List<ProductImage> images;


    public Product() {
    }

    public Product(int productId, String title, String description, double price, double shippingCost, int isDeleted, List<ProductSize> sizes, List<ProductImage> images) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.shippingCost = shippingCost;
        this.isDeleted = isDeleted;
        this.sizes = sizes;
        this.images = images;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<ProductSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<ProductSize> sizes) {
        this.sizes = sizes;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(productId);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeDouble(price);
        parcel.writeDouble(shippingCost);
        parcel.writeInt(isDeleted);
        parcel.writeTypedList(sizes);
        parcel.writeTypedList(images);
    }

    protected Product(Parcel in) {
        productId = in.readInt();
        title = in.readString();
        description = in.readString();
        price = in.readDouble();
        shippingCost = in.readDouble();
        isDeleted = in.readInt();
        sizes = in.createTypedArrayList(ProductSize.CREATOR);
        images = in.createTypedArrayList(ProductImage.CREATOR);
    }
}




