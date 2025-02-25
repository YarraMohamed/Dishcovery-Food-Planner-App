package com.example.foodplanner.plannedMeals.view;

import com.example.foodplanner.model.PlanMeals;

import java.util.List;

public interface PlansViewInterface {
    public void showPlannedMeals(List<PlanMeals> data);
    public void showError(String errMsg);
}
