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

//    public void getAreas(NetworkCallback networkCallback){
//       Call<AreaResponse> call = mealService.getAreas();
//       call.enqueue(new Callback<AreaResponse>() {
//           @Override
//           public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
//               networkCallback.onShowAreas(response.body());
//           }
//
//           @Override
//           public void onFailure(Call<AreaResponse> call, Throwable throwable) {
//               networkCallback.onFailure(throwable.getMessage());
//           }
//       });
//    }

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

    public void getMealByName(MealNetworkCallback networkCallback, String mealName){
        Call<MealResponse> call = mealService.getMealByName(mealName);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                networkCallback.onShowMealByName(response.body());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                networkCallback.onFailure(throwable.getMessage());
            }
        });
    }
}
