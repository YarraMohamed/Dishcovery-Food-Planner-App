package com.example.foodplanner.search.presenter;

import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.remote.NetworkCallback;
import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.search.view.SearchViewInterface;

public class SearchPresenter implements NetworkCallback {
    private SearchViewInterface searchViewInterface;
    private Repository repository;

    public SearchPresenter(SearchViewInterface searchViewInterface , Repository repository){
        this.searchViewInterface=searchViewInterface;
        this.repository=repository;
    }

    public void getCategories(){
       repository.getCategories(this);
    }

    public void getIngredients(){
        repository.getIngredinets(this);
    }

    public void getCountries(){
        repository.getCountry(this);
    }

    public void getCategoryMeal(String name){
        repository.getCategoryMeals(this,name);
    }

    public void getAreaMeal(String name){
        repository.getAreaMeals(this,name);
    }
    public void  getIngredientMeals(String name){
        repository.getIngMeals(this,name);
    }

    @Override
    public void onShowCategories(CategoryResponse categoryResponse) {
        searchViewInterface.showCategories(categoryResponse);
    }

    @Override
    public void onShowIngredients(IngredientResponse ingredientResponse) {
        searchViewInterface.showIngredients(ingredientResponse);
    }

    @Override
    public void onShowAreas(AreaResponse areaResponse) {
        searchViewInterface.showArea(areaResponse);
    }

    @Override
    public void onShowCategoryMeals(MealResponse mealResponse) {
        searchViewInterface.showCategoryMeals(mealResponse);
    }

    @Override
    public void onShowAreaMeals(MealResponse mealResponse) {
        searchViewInterface.showAreaMeals(mealResponse);
    }

    @Override
    public void onShowIngMeals(MealResponse mealResponse) {
        searchViewInterface.showIngMeals(mealResponse);
    }

    @Override
    public void onFailure(String errMsg) {
        searchViewInterface.showError(errMsg);
    }
    @Override
    public void onRandomMealSuccess(MealResponse mealResponse) {

    }

    @Override
    public void onSuggestedMealSuccess(MealResponse mealResponse) {

    }
}
