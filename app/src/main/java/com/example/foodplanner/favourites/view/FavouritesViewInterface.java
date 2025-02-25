package com.example.foodplanner.favourites.view;

import com.example.foodplanner.model.FavMeals;

import java.util.List;

public interface FavouritesViewInterface {
    public void showFavourites(List<FavMeals> favMeals);
    public void onError(String errMsg);
}
