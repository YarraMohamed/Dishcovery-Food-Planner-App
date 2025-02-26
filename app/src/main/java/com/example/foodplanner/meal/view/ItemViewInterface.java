package com.example.foodplanner.meal.view;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;

import java.util.List;

public interface ItemViewInterface {
    public void getMeal(Meal meal);
    public void showError(String err);
}
