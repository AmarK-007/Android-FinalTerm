package com.android.assignment1.shoecart.models;

public class HomeProduct {
    public int imageId;
    public String productName;
    public double cost;

    public HomeProduct(int _imageId, String _productName, double _cost){
        this.imageId = _imageId;
        this.productName = _productName;
        this.cost = _cost;
    }

    public int getImageId() {return  imageId;}
    public String getProductName() {return productName;}
    public double getCost(){return  cost;}
}
