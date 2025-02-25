package com.example.foodplanner.data;

import com.example.foodplanner.data.local.MealLocalDataSource;
import com.example.foodplanner.data.remote.MealNetworkCallback;
import com.example.foodplanner.data.remote.MealRemoteDataSource;
import com.example.foodplanner.data.remote.NetworkCallback;
import com.example.foodplanner.model.FavMeals;
import com.example.foodplanner.model.PlanMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class Repository {
   private MealRemoteDataSource mealRemoteDataSource;
   private MealLocalDataSource mealLocalDataSource;
   private static Repository instance = null;

   private Repository(MealRemoteDataSource mealRemoteDataSource , MealLocalDataSource mealLocalDataSource){
       this.mealRemoteDataSource=mealRemoteDataSource;
       this.mealLocalDataSource = mealLocalDataSource;
   }

   public static Repository getRepoInstance(MealRemoteDataSource mealRemoteDataSource , MealLocalDataSource mealLocalDataSource){
       if(instance==null){
           return new Repository(mealRemoteDataSource , mealLocalDataSource);
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

   public Completable addToFav(FavMeals favMeal){
       return mealLocalDataSource.addToFav(favMeal);
   }
   public Observable<List<FavMeals>> getStoredFavMeals(){
       return mealLocalDataSource.getStoredFavMeals();
   }
   public Completable removerFromFav(FavMeals favMeal){
       return mealLocalDataSource.removeFromFav(favMeal);
   }

   public Completable addToPlan(PlanMeals planMeal){
       return mealLocalDataSource.addToPlan(planMeal);
   }
   public Completable removeFromPlan(PlanMeals planMeal){
       return mealLocalDataSource.removeFromPlan(planMeal);
   }
    public Observable<List<PlanMeals>> getStoredPlanMealsForDay(String date){
        return mealLocalDataSource.getStoredPlanMealsForDay(date);
    }
}
