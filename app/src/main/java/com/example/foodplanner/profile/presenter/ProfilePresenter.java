package com.example.foodplanner.profile.presenter;

import android.content.SharedPreferences;

import com.example.foodplanner.profile.view.ProfileInterface;
import com.google.firebase.auth.FirebaseAuth;

public class ProfilePresenter {
    private FirebaseAuth auth;
    private SharedPreferences preferences;
    private ProfileInterface profileInterface;

    public ProfilePresenter(ProfileInterface profileInterface,SharedPreferences preferences){
        this.profileInterface = profileInterface;
        this.preferences=preferences;
        this.auth=FirebaseAuth.getInstance();
    }

    public void logout(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        auth.signOut();
        profileInterface.onLogout();
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
