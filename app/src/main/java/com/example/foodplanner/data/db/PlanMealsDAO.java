package com.example.foodplanner.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.airbnb.lottie.L;
import com.example.foodplanner.model.PlanMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface PlanMealsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public Completable addToPlan(PlanMeals planMeal);
    @Delete
    public Completable removeFromPlan(PlanMeals planMeal);
    @Query("SELECT * FROM plan_meals")
    public Observable<List<PlanMeals>> getStoredPlanMeals();
    @Query("SELECT * FROM plan_meals WHERE date = :selectedDate")
    Observable<List<PlanMeals>> getMealsForDate(String selectedDate);
    @Query("DELETE FROM plan_meals")
    public Completable deleteAllPlan();
}
