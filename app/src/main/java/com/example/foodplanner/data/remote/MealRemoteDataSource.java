package com.example.foodplanner.data.remote;

import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.IngredientResponse;
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

    public void getCategories(NetworkCallback networkCallback){
        Call<CategoryResponse> call = mealService.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                networkCallback.onShowCategories(response.body());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                networkCallback.onFailure(throwable.getMessage());
            }
        });
    }

    public void getIngredients(NetworkCallback networkCallback){
        Call<IngredientResponse> call = mealService.getIngredients();
        call.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                networkCallback.onShowIngredients(response.body());
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable throwable) {
                networkCallback.onFailure(throwable.getMessage());
            }
        });
    }
    public void getAreas(NetworkCallback networkCallback){
       Call<AreaResponse> call = mealService.getAreas();
       call.enqueue(new Callback<AreaResponse>() {
           @Override
           public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
               networkCallback.onShowAreas(response.body());
           }

           @Override
           public void onFailure(Call<AreaResponse> call, Throwable throwable) {
               networkCallback.onFailure(throwable.getMessage());
           }
       });
    }

    public void getCategoryMeals(NetworkCallback networkCallback , String name){
        Call<MealResponse> call = mealService.getMealsByCategory(name);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                networkCallback.onShowCategoryMeals(response.body());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                networkCallback.onFailure(throwable.getMessage());
            }
        });
    }

    public void getAreaMeals(NetworkCallback networkCallback, String name){
        Call<MealResponse> call = mealService.getMealsByArea(name);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                networkCallback.onShowAreaMeals(response.body());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                networkCallback.onFailure(throwable.getMessage());
            }
        });
    }

    public void getIngMeals(NetworkCallback networkCallback, String name){
        Call<MealResponse> call = mealService.getMealsByIngredient(name);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                networkCallback.onShowIngMeals(response.body());
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                networkCallback.onFailure(throwable.getMessage());
            }
        });
    }
}
