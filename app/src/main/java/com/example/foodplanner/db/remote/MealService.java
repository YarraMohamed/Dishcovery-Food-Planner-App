package com.example.foodplanner.db.remote;

import com.example.foodplanner.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {
    @GET("api/json/v1/1/random.php")
    Call<MealResponse> getRandomMeal();

    @GET("api/json/v1/1/search.php?f=k")
    Call<MealResponse> getSuggestedMeals();
}
