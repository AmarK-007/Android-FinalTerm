package com.android.assignment1.shoecart.models;

/**
 * Model class for HomeProduct
 */
public class HomeProduct {
    public int imageId;
    public String productName;
    public double cost;

    /**
     * Constructor for HomeProduct
     *
     * @param _imageId
     * @param _productName
     * @param _cost
     * @return
     */
    public HomeProduct(int _imageId, String _productName, double _cost) {
        this.imageId = _imageId;
        this.productName = _productName;
        this.cost = _cost;
    }

    // getter methods
    public int getImageId() {
        return imageId;
    }

    public String getProductName() {
        return productName;
    }

    public double getCost() {
        return cost;
    }
}
