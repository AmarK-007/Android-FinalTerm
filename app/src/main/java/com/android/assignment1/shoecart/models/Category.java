package com.android.assignment1.shoecart.models;

/**
 * Model class for Category
 */
public class Category {

    public int id;
    public String categoryImage;
    public String categoryName;

    /**
     * Constructor for Category
     *
     * @param _id
     * @param _categoryImage
     * @param _categoryName
     * @return
     */
    public Category(int _id, String _categoryImage, String _categoryName) {
        this.id = _id;
        this.categoryName = _categoryName;
        this.categoryImage = _categoryImage;
    }

    /**
     * getId method
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * getCategoryImage method
     *
     * @return
     */
    public String getCategoryImage() {
        return categoryImage;
    }

    /**
     * getCategoryName method
     *
     * @return
     */
    public String getCategoryName() {
        return categoryName;
    }
}
