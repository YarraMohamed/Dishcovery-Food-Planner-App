package com.example.foodplanner.data.remote;

import com.example.foodplanner.model.MealResponse;

public interface MealNetworkCallback {
    public void onShowMealByName(MealResponse mealResponse);
    public void onFailure(String errMsg);
}
