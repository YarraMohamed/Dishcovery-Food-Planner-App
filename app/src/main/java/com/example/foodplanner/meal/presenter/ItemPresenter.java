package com.example.foodplanner.meal.presenter;

import com.example.foodplanner.data.Repository;
import com.example.foodplanner.meal.view.ItemViewInterface;
import com.example.foodplanner.model.FavMeals;
import com.example.foodplanner.model.PlanMeals;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ItemPresenter {
    private Repository repo;
    private ItemViewInterface itemViewInterface;

    public ItemPresenter(ItemViewInterface itemViewInterface,Repository repository){
        this.itemViewInterface=itemViewInterface;
        this.repo=repository;
    }

    public void getMeal(String mealName){
        repo.getMealByName(mealName)
                .map(item->item.getMeals().get(0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->itemViewInterface.getMeal(item),
                        err->itemViewInterface.showError(err.getMessage())
                );
    }

    public void addToFav(FavMeals favMeal){
        repo.addToFav(favMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void uploadFavMeal(String userId, FavMeals favMeal){
        repo.uploadFavMeal(userId,favMeal)
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

    public void uploadPlanMeal(String userId,PlanMeals planMeal){
        repo.uploadPlanMeal(userId,planMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public String getInstructions(String instructions){

        String[] lines = instructions.split("\\.");
        StringBuilder newInstructions = new StringBuilder();

        for (String line : lines) {
            String trimmedLine = line.trim();
            if (!trimmedLine.isEmpty()) {
                newInstructions.append(trimmedLine).append(".\n");
            }
        }

        return newInstructions.toString();
    }
}
