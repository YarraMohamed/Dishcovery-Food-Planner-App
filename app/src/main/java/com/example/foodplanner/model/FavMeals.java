package com.example.foodplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_meal")
public class FavMeals {
    @PrimaryKey
    @NonNull
    String mealID;
    String mealName;
    String mealCountry;
    String mealCategory;
    String mealThumb;

    public FavMeals(){}

    public FavMeals(String mealID, String mealName, String mealCountry, String mealCategory, String mealThumb) {
        this.mealID = mealID;
        this.mealName = mealName;
        this.mealCountry = mealCountry;
        this.mealCategory = mealCategory;
        this.mealThumb = mealThumb;
    }

    public String getMealID() {
        return mealID;
    }

    public void setMealID(String mealID) {
        this.mealID = mealID;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealCountry() {
        return mealCountry;
    }

    public void setMealCountry(String mealCountry) {
        this.mealCountry = mealCountry;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getMealThumb() {
        return mealThumb;
    }

    public void setMealThumb(String mealThumb) {
        this.mealThumb = mealThumb;
    }
}
