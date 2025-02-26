package com.example.foodplanner.data.remote;

import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {
    public static final String BASE_URL ="https://www.themealdb.com/";
    private MealService mealService;

    public MealRemoteDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        mealService = retrofit.create(MealService.class);
    }

    public Single<MealResponse> getRandomMeal(){
        return mealService.getRandomMeall();
    }

    public Single<MealResponse> getSuggestedMeals(){
        return mealService.getSuggestedMeals();
    }
    public Single<CategoryResponse> getCategories(){
        return mealService.getCategories();
    }
    public Single<IngredientResponse> getIngredients(){
        return mealService.getIngredients();
    }
    public Single<AreaResponse> getCountries(){
        return mealService.getAreas();
    }

    public Single<MealResponse> getCategoryMeals(String category){
        return mealService.getMealsByCategory(category);
    }

    public Single<MealResponse> getAreaMeals(String country){
        return mealService.getMealsByArea(country);
    }

    public Single<MealResponse> getIngredientsMeals(String ingredient){
        return mealService.getMealsByIngredient(ingredient);
    }

    public Single<MealResponse> getMealByMeal(String name){
        return mealService.getMealByName(name);
    }
}
