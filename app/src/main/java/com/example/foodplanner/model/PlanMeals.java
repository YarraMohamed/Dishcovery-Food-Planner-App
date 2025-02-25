package com.example.foodplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "plan_meals")
public class PlanMeals {
    @PrimaryKey
    @NonNull
    String mealID;
    String mealName;
    String mealThumb;
    String date;

    public PlanMeals(){}

    public PlanMeals(String mealID, String mealName, String mealThumb) {
        this.mealID = mealID;
        this.mealName = mealName;
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

    public String getMealThumb() {
        return mealThumb;
    }

    public void setMealThumb(String mealThumb) {
        this.mealThumb = mealThumb;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
