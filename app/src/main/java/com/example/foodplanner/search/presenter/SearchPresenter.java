package com.example.foodplanner.search.presenter;

import com.example.foodplanner.data.Repository;
import com.example.foodplanner.search.view.SearchViewInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
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



//    public void getAreaMeal(String name){
//        repository.getAreaMeals(this,name);
//    }
//    public void  getIngredientMeals(String name){
//        repository.getIngMeals(this,name);
//    }


}
