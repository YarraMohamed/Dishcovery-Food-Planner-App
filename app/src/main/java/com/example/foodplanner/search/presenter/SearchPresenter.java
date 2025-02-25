package com.example.foodplanner.search.presenter;

import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.remote.NetworkCallback;
import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.search.view.SearchViewInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter implements NetworkCallback {
    private SearchViewInterface searchViewInterface;
    private Repository repository;

    public SearchPresenter(SearchViewInterface searchViewInterface , Repository repository){
        this.searchViewInterface=searchViewInterface;
        this.repository=repository;
    }

    public void getCategories(){
        repository.getCategories()
                .map(item->item.getCategories())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item-> searchViewInterface.showList(item),
                        err->searchViewInterface.showError(err.getMessage())
                );
    }

    public void getIngredients(){
        repository.getIngredients()
                .map(item->item.getIngredients())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->searchViewInterface.showList(item),
                        err->searchViewInterface.showError(err.getMessage())
                );
    }

    public void getCountries(){
        repository.getCountries()
                .map(item->item.getCountries())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->searchViewInterface.showList(item),
                        err->searchViewInterface.showError(err.getMessage())
                );
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

}
