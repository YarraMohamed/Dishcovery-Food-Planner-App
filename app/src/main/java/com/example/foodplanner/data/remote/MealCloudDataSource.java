package com.example.foodplanner.data.remote;

import com.airbnb.lottie.L;
import com.example.foodplanner.model.FavMeals;
import com.example.foodplanner.model.PlanMeals;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealCloudDataSource {
    private FirebaseFirestore firestore;

    public MealCloudDataSource(){
        this.firestore=FirebaseFirestore.getInstance();
    }

    public Completable uploadFavMeal(String userId, FavMeals favMeal){
        return Completable.create(item-> {
            firestore.collection("users")
                    .document(userId)
                    .collection("favMeals")
                    .document(favMeal.getMealID())
                    .set(favMeal)
                    .addOnCompleteListener(task->item.onComplete())
                    .addOnFailureListener(e-> item.onError(e));
        });
    }

    public Completable uploadPlanMeal (String userId, PlanMeals planMeal){
        return Completable.create(item-> {
            firestore.collection("users")
                    .document(userId)
                    .collection("planMeals")
                    .document(planMeal.getMealID())
                    .set(planMeal)
                    .addOnCompleteListener(task->item.onComplete())
                    .addOnFailureListener(e-> item.onError(e));
        });
    }

    public Completable removeUploadFavMeal(String userId, FavMeals favMeal){
        return Completable.create(item-> {
            firestore.collection("users")
                    .document(userId)
                    .collection("favMeals")
                    .document(favMeal.getMealID())
                    .delete()
                    .addOnCompleteListener(task->item.onComplete())
                    .addOnFailureListener(e-> item.onError(e));
        });
    }

    public Completable removeUploadedPlanMeal (String userId, PlanMeals planMeal){
        return Completable.create(item-> {
            firestore.collection("users")
                    .document(userId)
                    .collection("planMeals")
                    .document(planMeal.getMealID())
                    .delete()
                    .addOnCompleteListener(task->item.onComplete())
                    .addOnFailureListener(e-> item.onError(e));
        });
    }

    public Observable<List<FavMeals>> getUplodedFavMeals(String userID){
        return Observable.create(item -> {
            firestore.collection("users")
                    .document(userID)
                    .collection("favMeals")
                    .get()
                    .addOnSuccessListener(snapshots -> {
                        List<FavMeals> favList = new ArrayList<>();
                        for (DocumentSnapshot document : snapshots) {
                            FavMeals meal = document.toObject(FavMeals.class);
                            favList.add(meal);
                        }
                        item.onNext(favList);
                        item.onComplete();
                    })
                    .addOnFailureListener(e -> item.onError(e));
        });
    }

    public Observable<List<PlanMeals>> getUplodedPlanMeals(String userId){
        return Observable.create(item -> {
            firestore.collection("users")
                    .document(userId)
                    .collection("planMeals")
                    .get()
                    .addOnSuccessListener(snapshots -> {
                        List<PlanMeals> planList = new ArrayList<>();
                        for (DocumentSnapshot document : snapshots) {
                            PlanMeals meal = document.toObject(PlanMeals.class);
                            planList.add(meal);
                        }
                        item.onNext(planList);
                        item.onComplete();
                    })
                    .addOnFailureListener(e -> item.onError(e));
        });
    }

}
