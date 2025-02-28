package com.example.foodplanner.data.local;

import android.content.Context;

import com.airbnb.lottie.L;
import com.example.foodplanner.data.db.Database;
import com.example.foodplanner.data.db.FavMealsDAO;
import com.example.foodplanner.data.db.PlanMealsDAO;
import com.example.foodplanner.model.FavMeals;
import com.example.foodplanner.model.PlanMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealLocalDataSource {
    private Context context;
    private FavMealsDAO favMealsDAO;
    private PlanMealsDAO planMealsDAO;
    private Observable<List<PlanMeals>> planMeals;
    private Observable<List<FavMeals>> favMeals;

    public MealLocalDataSource(Context context){
        this.context = context;
        Database db = Database.getInstance(context);
        this.favMealsDAO = db.getFavMealsDAO();
        this.planMealsDAO = db.getPlanMealsDAO();
        favMeals = favMealsDAO.getStoredFavMeals();
        planMeals = planMealsDAO.getStoredPlanMeals();
    }

    public Completable addToFav(FavMeals favMeal){
        return favMealsDAO.addToFav(favMeal);
    }

    public Completable removeFromFav(FavMeals favMeal){
        return favMealsDAO.removeFromFav(favMeal);
    }

    public Observable<List<FavMeals>> getStoredFavMeals(){
        return favMealsDAO.getStoredFavMeals();
    }

    public Completable addToPlan(PlanMeals planMeal){
        return planMealsDAO.addToPlan(planMeal);
    }

    public Completable removeFromPlan(PlanMeals planMeal){
        return planMealsDAO.removeFromPlan(planMeal);
    }

    public Observable<List<PlanMeals>> getStoredPlanMeals(){
        return planMealsDAO.getStoredPlanMeals();
    }

    public Observable<List<PlanMeals>> getStoredPlanMealsForDay(String date){
        return planMealsDAO.getMealsForDate(date);
    }

    public Completable clearAllFavMeals(){
        return favMealsDAO.deleteAllFav();
    }

    public Completable clearAllPlanMeals(){
        return planMealsDAO.deleteAllPlan();
    }
}
