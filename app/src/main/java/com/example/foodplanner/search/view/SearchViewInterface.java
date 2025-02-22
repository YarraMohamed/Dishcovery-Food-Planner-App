package com.example.foodplanner.search.view;

import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;

public interface SearchViewInterface {
    public void showCategories(CategoryResponse categoryResponse);
    public void showIngredients(IngredientResponse ingredientResponse);
    public void showArea(AreaResponse areaResponse);
    public void showCategoryMeals(MealResponse mealResponse);
    public void showAreaMeals(MealResponse mealResponse);
    public void showIngMeals(MealResponse mealResponse);
    public void  showError(String err);
}
