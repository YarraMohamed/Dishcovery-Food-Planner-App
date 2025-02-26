package com.example.foodplanner.data.remote;

import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

    @GET("api/json/v1/1/random.php")
    Single<MealResponse> getRandomMeall();
    @GET("api/json/v1/1/search.php?f=k")
    Single<MealResponse> getSuggestedMeals();
    @GET("api/json/v1/1/categories.php")
    Single<CategoryResponse> getCategories();
    @GET("api/json/v1/1/list.php?i=list")
    Single<IngredientResponse> getIngredients();
    @GET("api/json/v1/1/list.php?a=list")
    Single<AreaResponse> getAreas();
    @GET("api/json/v1/1/filter.php")
    Single<MealResponse> getMealsByCategory(@Query("c") String category);
    @GET("api/json/v1/1/filter.php")
    Single<MealResponse> getMealsByArea(@Query("a") String Area);
    @GET("api/json/v1/1/filter.php")
    Single<MealResponse> getMealsByIngredient(@Query("i") String ingredient);
    @GET("/api/json/v1/1/search.php")
    Single<MealResponse> getMealByName(@Query("s")String mealName);

}
