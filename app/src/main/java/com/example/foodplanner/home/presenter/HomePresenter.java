package com.example.foodplanner.home.presenter;

import com.example.foodplanner.data.Repository;
import com.example.foodplanner.home.view.HomeViewInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    private Repository repository;
    HomeViewInterface homeViewInterface;

    public HomePresenter(HomeViewInterface homeViewInterface,Repository repository){
        this.homeViewInterface=homeViewInterface;
        this.repository=repository;
    }

    public void getRandomMeal(){
        repository.getRandomMeal()
                .map(item->item.getMeals().get(0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->homeViewInterface.showRandomMeal(item),
                        err->homeViewInterface.showError(err.getMessage())
                );
    }

    public void getSuggestedMeals(){
        repository.getSuggestedMeals()
                .map(item->item.getMeals())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> homeViewInterface.showSuggestedMeal(item),
                        err -> homeViewInterface.showError(err.getMessage())
                );
    }


}
