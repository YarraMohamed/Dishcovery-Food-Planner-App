package com.example.foodplanner.meal.view;

import com.example.foodplanner.model.MealResponse;

public interface ItemViewInterface {
    public void getMeal(MealResponse mealResponse);
    public void showError(String err);
}
