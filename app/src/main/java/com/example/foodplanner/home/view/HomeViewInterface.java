package com.example.foodplanner.home.view;

import com.example.foodplanner.model.MealResponse;

public interface HomeViewInterface {
    public void showRandomMeal(MealResponse mealResponse);
    public void showSuggestedMeal(MealResponse mealResponse);
    public void  showError(String err);
}
