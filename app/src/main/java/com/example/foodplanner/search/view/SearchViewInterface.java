package com.example.foodplanner.search.view;

import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Ingredient;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;

import java.util.List;

public interface SearchViewInterface<T> {
    public void showList(List<T> data);
    public void showCategoryMeals(MealResponse mealResponse);
    public void showAreaMeals(MealResponse mealResponse);
    public void showIngMeals(MealResponse mealResponse);
    public void  showError(String err);
}
