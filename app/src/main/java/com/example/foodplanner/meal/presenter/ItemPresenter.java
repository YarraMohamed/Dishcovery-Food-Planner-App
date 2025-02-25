package com.example.foodplanner.meal.presenter;

import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.remote.MealNetworkCallback;
import com.example.foodplanner.meal.view.ItemViewInterface;
import com.example.foodplanner.model.FavMeals;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.PlanMeals;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ItemPresenter implements MealNetworkCallback{
    private Repository repo;
    private ItemViewInterface itemViewInterface;

    public ItemPresenter(ItemViewInterface itemViewInterface,Repository repository){
        this.itemViewInterface=itemViewInterface;
        this.repo=repository;
    }

    public void getMeal(String mealName){
        repo.getMealByName(this,mealName);
    }

    public String getInstructions(String instructions){
        String[] steps = instructions.split("\\.\\s+");
        StringBuilder formattedInstructions = new StringBuilder();

        int n = 1;
        for (String step : steps) {
            if (!step.trim().isEmpty()) {
                formattedInstructions.append(n).append(". ").append(step.trim()).append(".\n");
                n++;
            }
        }

        return formattedInstructions.toString();
    }

    @Override
    public void onShowMealByName(MealResponse mealResponse) {
        itemViewInterface.getMeal(mealResponse);
    }

    @Override
    public void onFailure(String errMsg) {
        itemViewInterface.showError(errMsg);

    }
    public void addToFav(FavMeals favMeal){
        repo.addToFav(favMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void addToPlan(PlanMeals planMeal){
        repo.addToPlan(planMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
