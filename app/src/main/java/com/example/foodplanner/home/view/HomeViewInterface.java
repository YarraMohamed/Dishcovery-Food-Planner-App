package com.example.foodplanner.home.view;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;

import java.util.List;

public interface HomeViewInterface {
    public void showRandomMeal(Meal meal);
    public void showSuggestedMeal(List<Meal> meals);
    public void  showError(String err);
}
