package com.example.foodplanner.model;

public class MealConverter {
    public static FavMeals mealToFavMeal(Meal meal){
        return new FavMeals(
                meal.getIdMeal(),
                meal.getMealName(),
                meal.getArea(),
                meal.getCategory(),
                meal.getMealThumb()
        );
    }

    public static PlanMeals mealToPlanMeal(Meal meal){
        return new PlanMeals(
                meal.getIdMeal(),
                meal.getMealName(),
                meal.getMealThumb()
        );
    }
}
