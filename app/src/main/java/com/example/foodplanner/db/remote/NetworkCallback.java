package com.example.foodplanner.db.remote;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;

public interface NetworkCallback {
    public void onRandomMealSuccess(MealResponse mealResponse);
    public void onSuggestedMealSuccess(MealResponse mealResponse);
    public void onFailure(String errMsg);
}
