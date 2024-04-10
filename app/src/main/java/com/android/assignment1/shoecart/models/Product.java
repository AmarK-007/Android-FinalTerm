package com.android.assignment1.shoecart.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    String productId;
    ArrayList<String> imageArray= new ArrayList<>();
    String title;
    Double price;


    String description;
    String size;
    int quantity;

    public Product(String productId, ArrayList<String> imageArray, String title, Double price, String description, String size, int quantity) {
        this.productId = productId;
        this.imageArray = imageArray;
        this.title = title;
        this.price = price;
        this.description = description;
        this.size = size;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ArrayList<String> getImageArray() {
        return imageArray;
    }

    public void setImageArray(ArrayList<String> imageArray) {
        this.imageArray = imageArray;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", imageArray=" + imageArray +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
