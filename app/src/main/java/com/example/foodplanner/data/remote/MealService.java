package com.example.foodplanner.data.remote;

import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("api/json/v1/1/random.php")
    Call<MealResponse> getRandomMeal();
    @GET("api/json/v1/1/search.php?f=k")
    Call<MealResponse> getSuggestedMeals();
    @GET("api/json/v1/1/categories.php")
    Call<CategoryResponse> getCategories();
    @GET("api/json/v1/1/list.php?i=list")
    Call<IngredientResponse> getIngredients();
    @GET("api/json/v1/1/list.php?a=list")
    Call<AreaResponse> getAreas();
    @GET("api/json/v1/1/filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);
    @GET("api/json/v1/1/filter.php")
    Call<MealResponse> getMealsByArea(@Query("a") String Area);
    @GET("api/json/v1/1/filter.php")
    Call<MealResponse> getMealsByIngredient(@Query("i") String ingredient);

}
