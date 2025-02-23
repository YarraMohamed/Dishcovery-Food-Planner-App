package com.example.foodplanner.meal.presenter;

import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.remote.MealNetworkCallback;
import com.example.foodplanner.meal.view.ItemViewInterface;
import com.example.foodplanner.model.MealResponse;

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
}
