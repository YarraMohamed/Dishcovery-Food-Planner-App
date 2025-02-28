package com.example.foodplanner.profile.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.foodplanner.data.Repository;
import com.example.foodplanner.model.FavMeals;
import com.example.foodplanner.profile.view.ProfileInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenter {
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private SharedPreferences preferences;
    private ProfileInterface profileInterface;
    private Repository repository;
    private CompositeDisposable disposable;

    public ProfilePresenter(ProfileInterface profileInterface,SharedPreferences preferences,Repository repository){
        this.profileInterface = profileInterface;
        this.preferences=preferences;
        this.repository=repository;
        this.auth = FirebaseAuth.getInstance();
        this.firestore = FirebaseFirestore.getInstance();
        this.disposable = new CompositeDisposable();
    }

    public void logout(){
        removeAllFav();
        removeAllPlan();
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        auth.signOut();
        profileInterface.onLogout();
    }

    private void removeAllFav(){
        disposable.add(
                repository.removeAllFavMeals()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }

    private void removeAllPlan(){
        disposable.add(
                repository.removeAllPLanMeals()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }
    public Boolean checkAuth(){
        String userId = preferences.getString("userID","");
        if(userId.isEmpty() || userId.equals("")) {
            return true;
        }
        else {
            profileInterface.setUsername(auth.getCurrentUser().getEmail().split("@")[0]);
            return false;
        }
    }


}
