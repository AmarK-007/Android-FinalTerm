package com.android.assignment1.shoecart.models;

public class Category {

    public int id;
    public String categoryImage;
    public String categoryName;

    public Category(int _id, String _categoryImage, String _categoryName) {
        this.id = _id;
        this.categoryName = _categoryName;
        this.categoryImage = _categoryImage;
    }

    public int getId() {return id;}

    public String getCategoryImage() {
        return categoryImage;
    }
    public String getCategoryName() {return  categoryName;}
}
