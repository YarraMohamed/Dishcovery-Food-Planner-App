package com.example.foodplanner.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.FavMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface FavMealsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public Completable addToFav(FavMeals favMeal);
    @Delete
    public Completable removeFromFav(FavMeals favMeal);

    @Query("SELECT * FROM fav_meal")
    public Observable<List<FavMeals>> getStoredFavMeals();
}
