package com.example.foodplanner.favourites.presenter;

import com.example.foodplanner.data.Repository;
import com.example.foodplanner.favourites.view.FavouritesViewInterface;
import com.example.foodplanner.model.FavMeals;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavouritesPresenter {
    private Repository repository;
    private FavouritesViewInterface favouritesViewInterface;

    public FavouritesPresenter(FavouritesViewInterface favouritesViewInterface , Repository repository){
        this.favouritesViewInterface = favouritesViewInterface;
        this.repository = repository;
    }

    public void getStoredFav(){
        repository.getStoredFavMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> favouritesViewInterface.showFavourites(item),
                        err -> favouritesViewInterface.onError(err.getMessage())
                );
    }

    public void removeFromFav(FavMeals favMeals){
        repository.removerFromFav(favMeals)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
