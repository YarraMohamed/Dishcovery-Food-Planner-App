package com.example.foodplanner.data.remote;

import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;

public interface NetworkCallback {
//    public void onShowAreas(AreaResponse areaResponse);
    public void onShowCategoryMeals(MealResponse mealResponse);
    public void onShowAreaMeals(MealResponse mealResponse);
    public void onShowIngMeals(MealResponse mealResponse);
    public void onFailure(String errMsg);
}
