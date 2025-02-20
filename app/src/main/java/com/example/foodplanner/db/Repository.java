package com.example.foodplanner.db;

import com.example.foodplanner.db.remote.MealRemoteDataSource;
import com.example.foodplanner.db.remote.NetworkCallback;

public class Repository {
   private MealRemoteDataSource mealRemoteDataSource;
   private static Repository instance = null;

   private Repository(MealRemoteDataSource mealRemoteDataSource){
       this.mealRemoteDataSource=mealRemoteDataSource;
   }

   public static Repository getRepoInstance(MealRemoteDataSource mealRemoteDataSource){
       if(instance==null){
           return new Repository(mealRemoteDataSource);
       }
       return instance;
   }

   public void getRandomMeal(NetworkCallback networkCallback){
       mealRemoteDataSource.getRandomMealFromNetwork(networkCallback);
   }

   public void getSuggestedMeals(NetworkCallback networkCallback){
       mealRemoteDataSource.getSuggestedMealsFromNetwork(networkCallback);
   }
}
