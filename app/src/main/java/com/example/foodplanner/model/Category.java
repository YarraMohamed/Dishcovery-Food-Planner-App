package com.example.foodplanner.model;

import com.google.gson.annotations.SerializedName;

public class Category{
    private String idCategory;
    @SerializedName("strCategory")
    private String categoryName;
    @SerializedName("strCategoryThumb")
    private String categoryThumb;

    public Category(){

    }
    public Category(String idCategory, String categoryName, String categoryThumb) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.categoryThumb = categoryThumb;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryThumb() {
        return categoryThumb;
    }

    public void setCategoryThumb(String categoryThumb) {
        this.categoryThumb = categoryThumb;
    }

}
