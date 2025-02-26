package com.example.foodplanner.search.presenter;

import com.example.foodplanner.data.Repository;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.search.view.SearchViewInterface;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {
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

    public void getMealsByCategory(String category){
        repository.getCategoryMeals(category)
                .map(item->item.getMeals())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                  item->searchViewInterface.showList(item),
                  err->searchViewInterface.showError(err.getMessage())
                );
    }

    public void getMealsByCountry(String country){
        repository.getAreaMeals(country)
                .map(item->item.getMeals())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->searchViewInterface.showList(item),
                        err->searchViewInterface.showError(err.getMessage())
                );
    }

    public void getMealsByIngredient(String ingredient){
        repository.getIngredientMeals(ingredient)
                .map(item->item.getMeals())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->searchViewInterface.showList(item),
                        err->searchViewInterface.showError(err.getMessage())
                );
    }

    public void searchForCategory(String categorytxt){
        repository.getCategories()
                .map(item->item.getCategories())
                .map(item->item.stream()
                        .filter(category -> category.getCategoryName().toLowerCase().contains(categorytxt.toLowerCase()))
                        .collect(Collectors.toList()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->searchViewInterface.showList(item),
                        err->searchViewInterface.showError(err.getMessage())
                );
    }

    public void searchForCountry(String countryTxt){
        repository.getCountries()
                .map(item->item.getCountries())
                .map(item->item.stream()
                        .filter(country->country.getCountryName().toLowerCase().contains(countryTxt.toLowerCase()))
                        .collect(Collectors.toList()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->searchViewInterface.showList(item),
                        err->searchViewInterface.showError(err.getMessage())
                );
    }

    public void searchForIngredient(String ingredientTxt){
        repository.getIngredients()
                .map(item->item.getIngredients())
                .map(item->item.stream()
                        .filter(ingredient -> ingredient.getIngredientName().toLowerCase().contains(ingredientTxt.toLowerCase()))
                        .collect(Collectors.toList()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->searchViewInterface.showList(item),
                        err->searchViewInterface.showError(err.getMessage())
                );
    }

    public void searchForMealInCategory(String category, String mealName){
        repository.getCategoryMeals(category)
                .compose(transformer(mealName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->searchViewInterface.showList(item),
                        err->searchViewInterface.showError(err.getMessage())
                );
    }

    public void searchForMealInCountry(String country, String mealName){
        repository.getAreaMeals(country)
                .compose(transformer(mealName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->searchViewInterface.showList(item),
                        err->searchViewInterface.showError(err.getMessage())
                );
    }

    public void searchForMealInIngredient(String ingredient, String mealName){
        repository.getIngredientMeals(ingredient)
                .compose(transformer(mealName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->searchViewInterface.showList(item),
                        err->searchViewInterface.showError(err.getMessage())
                );
    }

    public void searchForMeal(String name){
        repository.getMealByName(name)
                .compose(transformer(name))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->searchViewInterface.showList(item),
                        err->searchViewInterface.showError(err.getMessage())
                );
    }
    private SingleTransformer<MealResponse,List> transformer(String mealSearch){
        return upstream -> upstream
                .map(item->item.getMeals())
                .map(item->item.stream()
                        .filter(meal->meal.getMealName().toLowerCase().contains(mealSearch.toLowerCase()))
                        .collect(Collectors.toList())
                );
    }
}
