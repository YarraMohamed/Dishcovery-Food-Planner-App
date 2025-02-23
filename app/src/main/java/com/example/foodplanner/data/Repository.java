package com.example.foodplanner.data;

import com.example.foodplanner.data.remote.MealNetworkCallback;
import com.example.foodplanner.data.remote.MealRemoteDataSource;
import com.example.foodplanner.data.remote.NetworkCallback;

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

   public void getCategories(NetworkCallback networkCallback){
       mealRemoteDataSource.getCategories(networkCallback);
   }

   public void getIngredinets(NetworkCallback networkCallback){
       mealRemoteDataSource.getIngredients(networkCallback);
   }

   public void getCountry(NetworkCallback networkCallback){
       mealRemoteDataSource.getAreas(networkCallback);
   }

   public void getCategoryMeals(NetworkCallback networkCallback , String name){
       mealRemoteDataSource.getCategoryMeals(networkCallback,name);
   }
   public void getAreaMeals(NetworkCallback networkCallback, String name){
       mealRemoteDataSource.getAreaMeals(networkCallback,name);
   }
   public void getIngMeals(NetworkCallback networkCallback, String name){
       mealRemoteDataSource.getIngMeals(networkCallback,name);
   }

   public void getMealByName(MealNetworkCallback networkCallback, String mealName){
       mealRemoteDataSource.getMealByName(networkCallback,mealName);
   }
}
