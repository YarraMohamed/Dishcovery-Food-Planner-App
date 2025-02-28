package com.example.foodplanner.plannedMeals.presenter;

import com.example.foodplanner.data.Repository;
import com.example.foodplanner.model.PlanMeals;
import com.example.foodplanner.plannedMeals.view.PlansViewInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlansPresenter {
    private Repository repository;
    private PlansViewInterface plansViewInterface;

    public PlansPresenter(PlansViewInterface plansViewInterface, Repository repository){
        this.plansViewInterface=plansViewInterface;
        this.repository=repository;
    }

    public void getStoredPlanForDay(String date){
        repository.getStoredPlanMealsForDay(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> plansViewInterface.showPlannedMeals(item),
                        err -> plansViewInterface.showError(err.getMessage())
                );
    }

    public void removePlan(PlanMeals planMeal){
        repository.removeFromPlan(planMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void removeUploadedPlanMeal(String userId, PlanMeals planMeal){
        repository.removeUplodedPlanMeal(userId, planMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
