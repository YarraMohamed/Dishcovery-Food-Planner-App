package com.example.foodplanner.model;

import com.google.gson.annotations.SerializedName;

public class Ingredient{
    @SerializedName("strIngredient")
    private String ingredientName;

    public Ingredient(){
    }
    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

}
