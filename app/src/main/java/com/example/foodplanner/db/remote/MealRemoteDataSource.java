package com.example.foodplanner.db.remote;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;

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
                .build();
        mealService = retrofit.create(MealService.class);

    }

    public void getRandomMealFromNetwork(NetworkCallback networkCallback){
        Call<MealResponse> call = mealService.getRandomMeal();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                networkCallback.onRandomMealSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                networkCallback.onFailure(throwable.getMessage());
            }
        });

    }

    public void getSuggestedMealsFromNetwork(NetworkCallback networkCallback){
        Call<MealResponse> call = mealService.getSuggestedMeals();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                networkCallback.onSuggestedMealSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                networkCallback.onFailure(throwable.getMessage());
            }
        });
    }
}
