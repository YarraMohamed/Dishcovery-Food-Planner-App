package com.example.foodplanner.home.presenter;

import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.remote.NetworkCallback;
import com.example.foodplanner.home.view.HomeViewInterface;
import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;

public class HomePresenter implements NetworkCallback {
    private Repository repository;
    HomeViewInterface homeViewInterface;

    public HomePresenter(HomeViewInterface homeViewInterface,Repository repository){
        this.homeViewInterface=homeViewInterface;
        this.repository=repository;
    }

    public void getRandomMeal(){
        repository.getRandomMeal(this);
    }

    public void getSuggestedMeals(){
        repository.getSuggestedMeals(this);
    }

    @Override
    public void onRandomMealSuccess(MealResponse mealResponse) {
        homeViewInterface.showRandomMeal(mealResponse);
    }

    @Override
    public void onSuggestedMealSuccess(MealResponse mealResponse) {
        homeViewInterface.showSuggestedMeal(mealResponse);
    }

    @Override
    public void onFailure(String errMsg) {
        homeViewInterface.showError(errMsg);
    }

    @Override
    public void onShowCategories(CategoryResponse categoryResponse) {

    }

    @Override
    public void onShowIngredients(IngredientResponse ingredientResponse) {

    }

    @Override
    public void onShowAreas(AreaResponse areaResponse) {

    }

    @Override
    public void onShowCategoryMeals(MealResponse mealResponse) {

    }

    @Override
    public void onShowAreaMeals(MealResponse mealResponse) {

    }

    @Override
    public void onShowIngMeals(MealResponse mealResponse) {

    }

}
