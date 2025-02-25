package com.example.foodplanner.data.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.FavMeals;
import com.example.foodplanner.model.PlanMeals;

@androidx.room.Database(entities = {FavMeals.class, PlanMeals.class},version = 2)
public abstract class Database extends RoomDatabase {
    private static Database instance = null;
    public abstract FavMealsDAO getFavMealsDAO();
    public abstract PlanMealsDAO getPlanMealsDAO();

    public static Database getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context, Database.class,"mealsDB").fallbackToDestructiveMigration().build();
        }
        return  instance;
    }
}
